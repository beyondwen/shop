package com.wenhao.shop.service.weixin.impl;

import com.wenhao.base.BaseApiService;
import com.wenhao.base.BaseResponse;
import com.wenhao.shop.sevice.weixin.service.WeixinService;
import com.wenhao.weixin.input.dto.AppInpDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeixinServiceImpl extends BaseApiService<AppInpDTO> implements WeixinService {

    @Value("${mayikt.weixin.name}")
    private String name;

    @Override
    public BaseResponse<AppInpDTO> getApp() {
        return setResultSuccess(new AppInpDTO("111111", "2222222222"));
    }
}
