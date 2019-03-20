package com.wenhao.member.mapper;

import com.wenhao.member.output.dto.UserOutDTO;
import com.wenhao.member.enity.UserDo;
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

}
