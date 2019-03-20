package com.wenhao.shop.sevice.weixin.service;

import com.wenhao.shop.core.base.BaseResponse;
import com.wenhao.shop.weixin.api.enity.AppEnity;
import feign.RequestLine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Api(tags = "微信服务")
public interface WeixinService {
    @ApiOperation(value = "微信app")
    @PostMapping("/getApp")
//    @RequestLine(value = "POST /getApp")
    BaseResponse<AppEnity> getApp();
}
