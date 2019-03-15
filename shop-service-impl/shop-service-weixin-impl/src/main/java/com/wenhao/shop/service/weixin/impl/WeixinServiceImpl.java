package com.wenhao.shop.service.weixin.impl;

import com.wenhao.shop.sevice.weixin.service.WeixinService;
import com.wenhao.shop.weixin.api.enitys.AppEnity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeixinServiceImpl implements WeixinService {

    @Override
    public AppEnity getApp() {
        AppEnity appEnity = new AppEnity();
        appEnity.setAppId("123456");
        appEnity.setAppName("test");
        return appEnity;
    }
}
