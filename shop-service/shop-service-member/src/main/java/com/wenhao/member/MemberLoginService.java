package com.wenhao.member;

import com.alibaba.fastjson.JSONObject;
import com.wenhao.base.BaseResponse;
import com.wenhao.member.input.dto.UserLoginInpDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "用户登录接口")
public interface MemberLoginService {

    @PostMapping("/login")
    @ApiOperation(value = "会员用户登录信息接口")
    BaseResponse<JSONObject> login(@RequestBody UserLoginInpDto userLoginInpDto);
}
