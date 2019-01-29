package com.wenhao.shop.sevice.member.service;

import com.wenhao.shop.weixin.entity.AppEnity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "会员服务")
public interface MemberService {

    @ApiOperation(value = "会员服务调用微信服务")
    @GetMapping("/memberToWeixin")
    AppEnity memberToWeixin();

}
