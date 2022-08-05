package com.cq.studyprocess.domain;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 程崎
 * @since 2022-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId
    private Long userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 头像：如果用户传入头像，那么就用户的，如果没传，那么就用一个默认的
     */
    private String avatar;

    /**
     * 用户介绍
     */
    private String description;

    /**
     * 标签
     */
    private String tags;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除字段：0未删除，1已删除
     */
    private Integer isDelete;


}
