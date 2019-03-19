package com.wenhao.shop.service.weixin.feign;

import com.wenhao.shop.sevice.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-member")
public interface MemberServiceFeign extends MemberService {
}
