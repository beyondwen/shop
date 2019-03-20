package com.wenhao.shop.sevice.weixin.service;

import com.wenhao.base.BaseResponse;
import com.wenhao.weixin.input.dto.AppInpDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;

@Api(tags = "微信服务")
public interface WeixinService {
    @ApiOperation(value = "微信app")
    @PostMapping("/getApp")
//    @RequestLine(value = "POST /getApp")
    BaseResponse<AppInpDTO> getApp();
}
