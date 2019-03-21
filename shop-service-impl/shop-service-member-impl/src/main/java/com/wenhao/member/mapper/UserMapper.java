package com.wenhao.member.mapper;

import com.wenhao.member.enity.UserDo;
import com.wenhao.member.output.dto.UserOutDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Insert("INSERT INTO `meite_user`(MOBILE,EMAIL,PASSWORD,USER_NAME,SEX,AGE,CREATE_TIME,UPDATE_TIME,IS_AVALIBLE,PIC_IMAGE,QQ_OPENID,WX_OPENID) VALUES (#{mobile}, #{email}, #{password}, #{userName}, null, null, null,null, '1', null, null, null);")
    int register(UserDo userDo);

    @Select("SELECT * FROM meite_user WHERE MOBILE=#{mobile};")
    UserOutDTO existMobile(@Param("mobile") String mobile);

    @Select("SELECT USER_ID as userId,MOBILE as mobile,EMAIL as email,PASSWORD as password,USER_NAME as userName FROM meite_user WHERE MOBILE=#{0} And PASSWORD=#{1};")
    UserDo login(@Param("mobile") String mobile, @Param("password") String password);

    @Select("SELECT USER_ID as userId,MOBILE as mobile,EMAIL as email,PASSWORD as password,USER_NAME as userName FROM meite_user WHERE USER_ID=#{0};")
    UserDo findByUserId(@Param("userId") Long userId);

}
