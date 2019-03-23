package com.wenhao.portal.controller;

import com.wenhao.base.BaseResponse;
import com.wenhao.member.feign.MemberServiceFeign;
import com.wenhao.member.output.dto.UserOutDTO;
import com.wenhao.web.base.BaseWebController;
import com.wenhao.web.constants.WebConstants;
import com.wenhao.web.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController extends BaseWebController {

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    /**
     * index页面
     */
    private static String INDEX_FTL = "index";

    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        String cookieValue = CookieUtils.getCookieValue(request, WebConstants.LOGIN_TOKEN_COOKIE_NAME, true);
        if (!StringUtils.isEmpty(cookieValue)) {
            BaseResponse<UserOutDTO> info = memberServiceFeign.getInfo(cookieValue);
            if (isSuccess(info)) {
                UserOutDTO data = info.getData();
                if (data != null) {
                    String mobile = data.getMobile();
                    String desensMobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                    model.addAttribute("desensMobile", desensMobile);
                }
            }
        }
        return INDEX_FTL;
    }
}
