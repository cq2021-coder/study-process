package com.cq.studyprocess.controller;


import com.cq.studyprocess.common.CommonResponse;
import com.cq.studyprocess.req.userProcess.UserProcessSaveReq;
import com.cq.studyprocess.service.UserProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
public class UserProcessController {

    @Resource
    private UserProcessService userProcessService;

    @PostMapping("/save")
    @ApiOperation("新增学习进度")
    public CommonResponse<String> save(UserProcessSaveReq req, HttpSession session) {
        userProcessService.saveUserProcess(req, session);
        return CommonResponse.success("新增成功！");
    }

}
