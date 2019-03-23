package com.wenhao.member.feign;

import com.wenhao.member.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-member")
public interface MemberServiceFeign extends MemberService {
}
