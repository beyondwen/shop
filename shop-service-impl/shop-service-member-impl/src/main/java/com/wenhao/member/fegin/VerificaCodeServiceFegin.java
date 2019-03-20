package com.wenhao.member.fegin;

import com.wenhao.shop.sevice.weixin.service.VerificaCodeService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "app-mayikt-weixin")
public interface VerificaCodeServiceFegin extends VerificaCodeService {


}