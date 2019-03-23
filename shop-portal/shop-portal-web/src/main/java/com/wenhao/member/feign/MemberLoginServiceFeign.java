package com.wenhao.member.feign;

import com.wenhao.member.MemberLoginService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-member")
public interface MemberLoginServiceFeign extends MemberLoginService {
}
