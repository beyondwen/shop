package com.wenhao.member.controller.req.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginVo {

    /**
     * 手机号码
     */
    @NotNull(message = "手机号不能为空!")
    private String mobile;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空!")
    private String password;

    /**
     * 图形验证码
     */
    @NotBlank(message = "图形验证码不能为空!")
    private String graphicCode;

}
