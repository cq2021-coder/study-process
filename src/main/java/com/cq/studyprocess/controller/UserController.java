package com.cq.studyprocess.controller;


import com.cq.studyprocess.common.CommonResponse;
import com.cq.studyprocess.domain.User;
import com.cq.studyprocess.req.UserRegisterReq;
import com.cq.studyprocess.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 程崎
 * @since 2022-08-05
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public CommonResponse<User> register(@RequestBody @Valid UserRegisterReq registerReq) {
        userService.register(registerReq);
        return CommonResponse.success("用户注册成功！");
    }

}
