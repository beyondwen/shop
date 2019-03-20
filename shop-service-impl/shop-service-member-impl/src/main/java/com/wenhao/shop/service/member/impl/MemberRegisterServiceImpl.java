package com.wenhao.shop.service.member.impl;

import com.alibaba.fastjson.JSONObject;
import com.wenhao.shop.core.base.BaseApiService;
import com.wenhao.shop.core.base.BaseResponse;
import com.wenhao.shop.core.constants.Constants;
import com.wenhao.shop.core.utils.MD5Util;
import com.wenhao.shop.member.api.enity.UserEntity;
import com.wenhao.shop.service.member.fegin.VerificaCodeServiceFegin;
import com.wenhao.shop.service.member.mapper.UserMapper;
import com.wenhao.shop.sevice.member.service.MemberRegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificaCodeServiceFegin verificaCodeServiceFegin;

    @Transactional
    public BaseResponse<JSONObject> register(@RequestBody UserEntity userEntity, String registCode) {
        //参数验证
        String userName = userEntity.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return setResultError("用户名称不能为空");
        }
        String mobile = userEntity.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号不能为空");
        }
        String password = userEntity.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空");
        }
        //验证注册码是否正确
        BaseResponse<JSONObject> verificaWeixinCode = verificaCodeServiceFegin.verificaWeixinCode(mobile, registCode);
        if (!verificaWeixinCode.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return setResultError(verificaWeixinCode.getMsg());
        }
        //对用户密码加密
        String newPassword = MD5Util.MD5(password);
        userEntity.setPassword(newPassword);
        //更新数据库
        return userMapper.register(userEntity) > 0 ? setResultSuccess("注册成功") : setResultError("注册失败");
    }
}
