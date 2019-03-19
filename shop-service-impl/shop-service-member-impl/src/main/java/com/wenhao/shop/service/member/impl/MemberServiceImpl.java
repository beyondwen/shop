package com.wenhao.shop.service.member.impl;

import com.wenhao.shop.core.base.BaseApiService;
import com.wenhao.shop.core.base.BaseResponse;
import com.wenhao.shop.core.constants.Constants;
import com.wenhao.shop.member.api.enity.UserEntity;
import com.wenhao.shop.service.member.fegin.WeixinFegin;
import com.wenhao.shop.service.member.mapper.UserMapper;
import com.wenhao.shop.sevice.member.service.MemberService;
import com.wenhao.shop.weixin.api.enity.AppEnity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl extends BaseApiService<UserEntity> implements MemberService {

    @Autowired
    private WeixinFegin weixinFegin;

    @Autowired
    private UserMapper userMapper;


    @Override
    public BaseResponse<AppEnity> memberToWeixin() {
        return weixinFegin.getApp();
    }

    @Override
    public BaseResponse<UserEntity> existMobile(String mobile) {
        // 1.验证参数
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        UserEntity userEntity = userMapper.existMobile(mobile);
        if (userEntity == null) {
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_202, "用户不存在");
        }
        // 注意需要将敏感数据进行脱敏
        userEntity.setPassword(null);
        return setResultSuccess(userEntity);
    }

}
