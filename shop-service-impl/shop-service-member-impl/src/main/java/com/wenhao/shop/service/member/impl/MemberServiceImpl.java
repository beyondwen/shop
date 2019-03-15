package com.wenhao.shop.service.member.impl;

import com.wenhao.shop.service.member.fegin.WeixinFegin;
import com.wenhao.shop.sevice.member.service.MemberService;
import com.wenhao.shop.weixin.api.enitys.AppEnity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl implements MemberService {

    @Autowired
    private WeixinFegin weixinFegin;

    @Override
    public AppEnity memberToWeixin() {
        return weixinFegin.getApp();
    }
}
