package com.cq.studyprocess.controller;


import com.cq.studyprocess.annotations.AdminRole;
import com.cq.studyprocess.common.CommonResponse;
import com.cq.studyprocess.req.user.UserLoginReq;
import com.cq.studyprocess.req.user.UserQueryAllReq;
import com.cq.studyprocess.req.user.UserRegisterReq;
import com.cq.studyprocess.req.user.UserUpdateReq;
import com.cq.studyprocess.resp.PageResp;
import com.cq.studyprocess.resp.UserQueryResp;
import com.cq.studyprocess.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "users")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    @CacheEvict(allEntries = true)
    public CommonResponse<String> register(@RequestBody @Valid UserRegisterReq registerReq) {
        userService.register(registerReq);
        return CommonResponse.success("用户注册成功！");
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public CommonResponse<UserQueryResp> login(@RequestBody @Valid UserLoginReq req, HttpSession session) {
        return CommonResponse.success(userService.login(req, session), "登录成功！");
    }

    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public CommonResponse<String> logout(HttpSession session) {
        userService.logout(session);
        return CommonResponse.success("退出成功！");
    }

    @GetMapping("/query-all")
    @ApiOperation("查询所有用户")
    @AdminRole
    @Cacheable(key = "#req.page+'_'+#req.size+'_query_page'")
    public CommonResponse<PageResp<UserQueryResp>> queryAll(UserQueryAllReq req, HttpSession session) {
        return CommonResponse.success(userService.queryAll(req, session), "查询成功！");
    }

    @GetMapping("/query-id/{userId}")
    @ApiOperation("根据id查询用户")
    @AdminRole
    @Cacheable(key = "#userId+'_query_by_id'")
    public CommonResponse<UserQueryResp> queryById(@PathVariable Long userId) {
        return CommonResponse.success(userService.queryById(userId), "根据id查询成功！");
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation("根据id批量删除用户")
    @CacheEvict(allEntries = true)
    public CommonResponse<String> deleteByIds(@PathVariable List<Long> ids) {
        userService.deleteByIds(ids);
        return CommonResponse.success("删除成功！");
    }

    @PostMapping("/update")
    @ApiOperation("根据id更新用户信息")
    @CacheEvict(allEntries = true)
    public CommonResponse<String> updateById(@RequestBody @Valid UserUpdateReq req) {
        userService.updateUser(req);
        return CommonResponse.success("更新成功！");
    }


}
