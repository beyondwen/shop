package com.wenhao.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.wenhao.base.BaseResponse;
import com.wenhao.member.controller.req.vo.RegisterVo;
import com.wenhao.member.feign.MemberRegisterServiceFeign;
import com.wenhao.member.input.dto.UserInpDTO;
import com.wenhao.web.base.BaseWebController;
import com.wenhao.web.bean.MeiteBeanUtils;
import com.wenhao.web.utils.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController extends BaseWebController {

    @Autowired
    private MemberRegisterServiceFeign memberRegisterServiceFeign;

    private static final String MEMBER_REGISTER_PAGE = "member/register";

    /**
     * 跳转到登陆页面页面
     */
    private static final String MB_LOGIN_FTL = "member/login";


    /**
     * 跳转到注册页面
     *
     * @return
     */
    @GetMapping("/register.html")
    public String getRegister() {
        return MEMBER_REGISTER_PAGE;
    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @PostMapping("/register.html")
    public String postRegister(@ModelAttribute(value = "registerVo") RegisterVo registerVo,
                               BindingResult bindingResult, Model model, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            String errMsg = bindingResult.getFieldError().getDefaultMessage();
            setErrorMsg(model, errMsg);
            return MEMBER_REGISTER_PAGE;
        }
        String graphicCode = registerVo.getGraphicCode();
        Boolean checkVerify = RandomValidateCodeUtil.checkVerify(graphicCode, httpSession);
        if (!checkVerify) {
            setErrorMsg(model, "图形验证码不正确");
            return MEMBER_REGISTER_PAGE;
        }
        UserInpDTO userInpDTO = MeiteBeanUtils.voToDto(registerVo, UserInpDTO.class);
        BaseResponse<JSONObject> register = memberRegisterServiceFeign.register(userInpDTO, registerVo.getRegistCode());
        if (!isSuccess(register)) {
            setErrorMsg(model, register.getMsg());
            return MEMBER_REGISTER_PAGE;
        }
        return MB_LOGIN_FTL;
    }

}
