package com.wenhao.base;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDo {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 是否可用
     */
    private String isAvailable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
