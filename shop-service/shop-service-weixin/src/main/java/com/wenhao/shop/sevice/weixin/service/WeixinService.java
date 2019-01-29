package com.wenhao.shop.sevice.weixin.service;

import com.wenhao.shop.weixin.entity.AppEnity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "微信服务")
public interface WeixinService {
    @ApiOperation(value = "微信app")
    @GetMapping("/getApp")
    AppEnity getApp();
}
