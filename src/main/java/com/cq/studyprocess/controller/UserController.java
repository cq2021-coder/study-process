package com.cq.studyprocess.controller;


import com.cq.studyprocess.annotations.AdminRole;
import com.cq.studyprocess.common.CommonResponse;
import com.cq.studyprocess.domain.User;
import com.cq.studyprocess.req.UserLoginReq;
import com.cq.studyprocess.req.UserQueryAllReq;
import com.cq.studyprocess.req.UserRegisterReq;
import com.cq.studyprocess.resp.PageResp;
import com.cq.studyprocess.resp.UserQueryAllResp;
import com.cq.studyprocess.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

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
@Api(tags = "用户接口")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public CommonResponse<User> register(@RequestBody @Valid UserRegisterReq registerReq) {
        userService.register(registerReq);
        return CommonResponse.success("用户注册成功！");
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public CommonResponse<User> login(@RequestBody @Valid UserLoginReq req, HttpSession session) {
        return CommonResponse.success(userService.login(req, session), "登录成功！");
    }

    @PostMapping("/logout")
    public CommonResponse<String> logout(HttpSession session) {
        userService.logout(session);
        return CommonResponse.success("退出成功！");
    }

    @GetMapping("/query-all")
    @ApiOperation("查询所有用户")
    @AdminRole
    public CommonResponse<PageResp<UserQueryAllResp>> queryAll(UserQueryAllReq req, HttpSession session) {
        return CommonResponse.success(userService.queryAll(req, session), "查询成功！");
    }


}
