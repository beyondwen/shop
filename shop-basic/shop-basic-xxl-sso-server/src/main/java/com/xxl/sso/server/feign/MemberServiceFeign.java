package com.xxl.sso.server.feign;

import com.wenhao.member.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-member")
public interface MemberServiceFeign extends MemberService {
}