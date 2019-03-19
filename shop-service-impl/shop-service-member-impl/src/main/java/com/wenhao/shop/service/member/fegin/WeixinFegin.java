package com.wenhao.shop.service.member.fegin;

import com.wenhao.shop.sevice.weixin.service.WeixinService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-mayikt-weixin")
public interface WeixinFegin extends WeixinService {

}
