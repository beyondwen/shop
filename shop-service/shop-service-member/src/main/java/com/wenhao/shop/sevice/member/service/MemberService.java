package com.wenhao.shop.sevice.member.service;

import com.wenhao.shop.core.base.BaseResponse;
import com.wenhao.shop.weixin.api.enity.AppEnity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "会员服务")
public interface MemberService {

    @ApiOperation(value = "会员服务调用微信服务")
    @GetMapping("/memberToWeixin")
    BaseResponse<AppEnity> memberToWeixin();

}
