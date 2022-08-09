package com.cq.studyprocess.req.user;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

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
public class UserRegisterReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名称
     */
    @NotBlank(message = "昵称不能为空")
    private String username;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", message = "邮箱格式错误")
    private String email;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
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
}
