package com.cq.studyprocess.resp;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value="UserQueryAllResp对象", description="用户表")
public class UserQueryAllResp implements Serializable {

    private static final long serialVersionUID = 32421L;

    @ApiModelProperty(value = "用户id")
    @TableId
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "邮箱")
    private String email;

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


}
