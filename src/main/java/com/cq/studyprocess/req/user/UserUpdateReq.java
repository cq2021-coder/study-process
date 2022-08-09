package com.cq.studyprocess.req.user;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 程崎
 * @since 2022/08/08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserUpdateReq对象", description="用户表")
public class UserUpdateReq implements Serializable {

    private static final long serialVersionUID = 145489196L;

    @ApiModelProperty(value = "用户id")
    @TableId
    @NotNull(message = "id不能为空")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "邮箱")
    @Pattern(regexp = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", message = "邮箱格式错误")
    private String email;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "头像：如果用户传入头像，那么就用户的，如果没传，那么就用一个默认的")
    private String avatar;

    @ApiModelProperty(value = "用户介绍")
    private String description;

    @ApiModelProperty(value = "标签")
    private String tags;


}
