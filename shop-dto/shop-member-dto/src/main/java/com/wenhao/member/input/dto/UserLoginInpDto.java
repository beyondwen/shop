package com.wenhao.member.input.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录
 */

@Data
@ApiModel(value = "用户登录参数")
public class UserLoginInpDto {

    @ApiModelProperty(value = "手机号码")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "登录类型")
    private String loginType;
    @ApiModelProperty(value = "设备信息")
    private String deviceInfo;


}
