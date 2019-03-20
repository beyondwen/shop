package com.wenhao.core.bean;

import org.springframework.beans.BeanUtils;

public class MeiteBeanUtils<Dto, Do> {

    public static <Do> Do dtoToDo(Object dtoEntity, Class<Do> doClass) {
        //判断是否为空
        if (dtoEntity == null) {
            return null;
        }
        //判断doClass是否为空
        if (doClass == null) {
            return null;
        }
        try {
            Do newInstance = doClass.newInstance();
            BeanUtils.copyProperties(dtoEntity, newInstance);
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

    public static <Dto> Dto doToDto(Object doEntity, Class<Dto> dtoClass) {
        //判断是否为空
        if (doEntity == null) {
            return null;
        }
        //判断doClass是否为空
        if (dtoClass == null) {
            return null;
        }
        try {
            Dto newInstance = dtoClass.newInstance();
            BeanUtils.copyProperties(doEntity, newInstance);
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }
}
