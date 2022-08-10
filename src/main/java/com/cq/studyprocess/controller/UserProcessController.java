package com.cq.studyprocess.controller;


import com.cq.studyprocess.annotations.AdminRole;
import com.cq.studyprocess.common.CommonResponse;
import com.cq.studyprocess.req.PageReq;
import com.cq.studyprocess.req.userProcess.UserProcessQueryReq;
import com.cq.studyprocess.req.userProcess.UserProcessSaveReq;
import com.cq.studyprocess.req.userProcess.UserProcessUpdateReq;
import com.cq.studyprocess.resp.PageResp;
import com.cq.studyprocess.resp.UserProcessQueryResp;
import com.cq.studyprocess.service.UserProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * <p>
 * 学习进度表 前端控制器
 * </p>
 *
 * @author 程崎
 * @since 2022-08-08
 */
@RestController
@RequestMapping("/user-process")
@Api(tags = "学习进度接口")
@CacheConfig(cacheNames = "user-process")
public class UserProcessController {

    @Resource
    private UserProcessService userProcessService;

    @PostMapping("/save")
    @ApiOperation("新增学习进度")
    @CacheEvict(allEntries = true)
    public CommonResponse<String> save(@RequestBody @Valid UserProcessSaveReq req, HttpSession session) {
        userProcessService.saveUserProcess(req, session);
        return CommonResponse.success("新增成功！");
    }

    @PostMapping("/update")
    @ApiOperation("修改学习进度")
    @CacheEvict(allEntries = true)
    public CommonResponse<String> update(@RequestBody @Valid UserProcessUpdateReq req, HttpSession session) {
        userProcessService.updateUserProcess(req, session);
        return CommonResponse.success("修改成功！");
    }

    @PostMapping("/query-page")
    @ApiOperation("分页查询学习进度")
    @AdminRole
    @Cacheable(key = "#req.page+'_'+#req.size+'_query_page'")
    public CommonResponse<PageResp<UserProcessQueryResp>> queryPage(UserProcessQueryReq req) {
        return CommonResponse.success(userProcessService.queryByPage(req), "查询成功！");
    }

    @GetMapping("/query-session")
    @ApiOperation("查询当前用户的学习进度")
    @Cacheable(key = "#req.page+'_'+#req.size+'_query_session'")
    public CommonResponse<PageResp<UserProcessQueryResp>> queryPageSession(PageReq req, HttpSession session) {
        return CommonResponse.success(userProcessService.queryBySession(req, session), "查询成功！");
    }


}
