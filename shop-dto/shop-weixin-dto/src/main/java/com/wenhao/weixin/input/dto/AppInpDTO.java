package com.wenhao.weixin.input.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户信息输入实体类")
public class AppInpDTO {

    public AppInpDTO(String appId, String appName) {
        this.appId = appId;
        this.appName = appName;
    }

    @ApiModelProperty(value = "应用id")
    private String appId;

    @ApiModelProperty(value = "应用名称")
    private String appName;
}
