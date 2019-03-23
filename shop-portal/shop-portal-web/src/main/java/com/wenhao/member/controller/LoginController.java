package com.wenhao.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.wenhao.base.BaseResponse;
import com.wenhao.constants.Constants;
import com.wenhao.member.controller.req.vo.LoginVo;
import com.wenhao.member.feign.MemberLoginServiceFeign;
import com.wenhao.member.input.dto.UserLoginInpDto;
import com.wenhao.web.base.BaseWebController;
import com.wenhao.web.bean.MeiteBeanUtils;
import com.wenhao.web.constants.WebConstants;
import com.wenhao.web.utils.CookieUtils;
import com.wenhao.web.utils.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController extends BaseWebController {
    private static final String MEMBER_LOGIN_PAGE = "member/login";

    /**
     * 重定向到首页
     */
    private static final String REDIRECT_INDEX = "redirect:/";


    @Autowired
    private MemberLoginServiceFeign memberLoginServiceFeign;

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @GetMapping("/login")
    public String getLogin() {
        return MEMBER_LOGIN_PAGE;
    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @PostMapping("/login")
    public String postLogin(@ModelAttribute("loginVo") LoginVo loginVo, Model model, HttpServletRequest request,
                            HttpServletResponse response, HttpSession httpSession) {
        // 1.图形验证码判断
        String graphicCode = loginVo.getGraphicCode();
        if (!RandomValidateCodeUtil.checkVerify(graphicCode, httpSession)) {
            setErrorMsg(model, "图形验证码不正确!");
            return MEMBER_LOGIN_PAGE;
        }
        // 2.将vo转换为dto
        UserLoginInpDto voToDto = MeiteBeanUtils.voToDto(loginVo, UserLoginInpDto.class);
        voToDto.setLoginType(Constants.MEMBER_LOGIN_TYPE_PC);
        String info = webBrowserInfo(request);
        voToDto.setDeviceInfo(info);
        BaseResponse<JSONObject> login = memberLoginServiceFeign.login(voToDto);
        if (!isSuccess(login)) {
            setErrorMsg(model, login.getMsg());
            return MEMBER_LOGIN_PAGE;
        }
        JSONObject data = login.getData();
        String token = data.getString("token");
        CookieUtils.setCookie(request, response, WebConstants.LOGIN_TOKEN_COOKIE_NAME, token);
        return REDIRECT_INDEX;
    }
}

