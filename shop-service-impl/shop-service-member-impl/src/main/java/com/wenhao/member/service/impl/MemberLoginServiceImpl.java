package com.wenhao.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wenhao.base.BaseApiService;
import com.wenhao.base.BaseResponse;
import com.wenhao.constants.Constants;
import com.wenhao.core.token.GenerateToken;
import com.wenhao.core.utils.MD5Util;
import com.wenhao.member.MemberLoginService;
import com.wenhao.member.enity.UserDo;
import com.wenhao.member.input.dto.UserLoginInpDto;
import com.wenhao.member.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberLoginServiceImpl extends BaseApiService<JSONObject> implements MemberLoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GenerateToken generateToken;

    public BaseResponse<JSONObject> login(@RequestBody UserLoginInpDto userLoginInpDto) {
        //验证手机号和密码
        String mobile = userLoginInpDto.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空");
        }
        String password = userLoginInpDto.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空");
        }
        String logintype = userLoginInpDto.getLogintype();
        if (StringUtils.isEmpty(logintype)) {
            return setResultError("登录类型不能为空");
        }
        if (!(logintype.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID)
                || logintype.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
                || logintype.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
            return setResultError("登录类型错误");
        }
        //校验加密密码
        String newPassword = MD5Util.MD5(password);
        UserDo userDo = userMapper.login(mobile, newPassword);
        if (userDo == null) {
            return setResultError("用户不存在");
        }
        //获取userid
        Long userId = userDo.getUserId();
        String keyPrefix = Constants.MEMBER_TOKEN_KEYPREFIX + logintype;
        String token = generateToken.createToken(keyPrefix, userId + "");
        JSONObject data = new JSONObject();
        data.put("token", token);
        return setResultSuccess(data);
    }
}
