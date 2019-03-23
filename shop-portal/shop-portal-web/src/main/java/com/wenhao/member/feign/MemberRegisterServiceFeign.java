package com.wenhao.member.feign;

import com.wenhao.member.MemberRegisterService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-member")
public interface MemberRegisterServiceFeign extends MemberRegisterService {
}
