package com.cq.studyprocess.req;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户查询所有要求
 *
 * @author 程崎
 * @since 2022/08/06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserQueryAllReq extends PageReq implements Serializable{

    private static final long serialVersionUID = 4892318935132L;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "普通用户是0，管理员是1")
    private Integer roles;
}
