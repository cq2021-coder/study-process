package com.cq.studyprocess.controller;

import com.cq.studyprocess.common.CommonResponse;
import com.cq.studyprocess.exception.BusinessCode;
import com.cq.studyprocess.exception.BusinessException;
import com.cq.studyprocess.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公共控制器
 *
 * @author 程崎
 * @since 2022/08/08
 */
@RestController
@RequestMapping("/common")
@Api(tags = "公共接口")
public class CommonController {
    @Resource
    private CommonService commonService;

    @ApiOperation("上传文件接口")
    @PostMapping("/upload")
    public CommonResponse<String> upload(MultipartFile file) {
        return CommonResponse.success(commonService.upload(file), "上传成功！");
    }

    @ApiOperation("下载文件接口")
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {

        try {
            commonService.download(name, response);
        } catch (IOException e) {
            throw new BusinessException(BusinessCode.FILE_ERROR,"获取文件错误！");
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除文件")
    public CommonResponse<Boolean> deleteFile(String name) {
        return CommonResponse.success(commonService.deleteFile(name),"文件已删除");
    }
}
