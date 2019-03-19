package com.wenhao.shop.service.member.mapper;

import com.wenhao.shop.member.api.enity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Insert("INSERT INTO `meite_user`(MOBIL,EMAIL,PASSWORD,USER_NAME,SEX,AGE,CREATE_TIME,UPDATE_TIME,IS_AVALIBLE,PIC_IMAGE,QQ_OPENID,WX_OPENID) VALUES (#{mobile}, #{email}, #{password}, #{userName}, null, null, null,null, '1', null, null, null);")
    int register(UserEntity userEntity);

    @Select("SELECT * FROM meite_user WHERE MOBILE=#{mobile};")
    UserEntity existMobile(@Param("mobile") String mobile);

}
