package com.wenhao.shop.service.weixin.impl;

import com.wenhao.shop.core.base.BaseApiService;
import com.wenhao.shop.core.base.BaseResponse;
import com.wenhao.shop.sevice.weixin.service.WeixinService;
import com.wenhao.shop.weixin.api.enitys.AppEnity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeixinServiceImpl extends BaseApiService<AppEnity> implements WeixinService {

    @Value("${mayikt.weixin.name}")
    private String name;

    @Override
    public BaseResponse<AppEnity> getApp() {
        return setResultSuccess(new AppEnity("111111", "2222222222"));
    }
}
