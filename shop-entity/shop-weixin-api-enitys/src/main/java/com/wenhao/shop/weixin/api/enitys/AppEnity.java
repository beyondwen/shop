package com.wenhao.shop.weixin.api.enitys;

import lombok.Data;

@Data
public class AppEnity {

    private String appName;

    private String appId;

    public AppEnity(String appName, String appId) {
        this.appName = appName;
        this.appId = appId;
    }
}
