package com.cq.studyprocess.domain;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 程崎
 * @since 2022-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "头像：如果用户传入头像，那么就用户的，如果没传，那么就用一个默认的")
    private String avatar;

    @ApiModelProperty(value = "用户介绍")
    private String description;

    @ApiModelProperty(value = "标签")
    private String tags;

    @ApiModelProperty(value = "普通用户是0，管理员是1")
    private Integer roles;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除字段：0未删除，1已删除")
    private Integer isDelete;


}
