package com.cq.studyprocess.controller;


import com.cq.studyprocess.common.CommonResponse;
import com.cq.studyprocess.req.tag.TagsQueryPageReq;
import com.cq.studyprocess.req.tag.TagsSaveOrUpdateReq;
import com.cq.studyprocess.resp.PageResp;
import com.cq.studyprocess.resp.TagsQueryResp;
import com.cq.studyprocess.service.TagsService;
import com.cq.studyprocess.utils.CopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author 程崎
 * @since 2022-08-08
 */
@RestController
@RequestMapping("/tags")
@Api(tags = "标签接口")
public class TagsController {

    @Resource
    private TagsService tagsService;

    @PostMapping("/edit")
    @ApiOperation("新增或修改标签，不传tagId为新增")
    public CommonResponse<String> saveOrUpdate(@RequestBody @Valid TagsSaveOrUpdateReq req) {
        tagsService.saveOrUpdateTag(req);
        return CommonResponse.success("编辑成功！");
    }

    @GetMapping("/query")
    @ApiOperation("分页查询标签")
    public CommonResponse<PageResp<TagsQueryResp>> queryByPage(TagsQueryPageReq req) {
        return CommonResponse.success(tagsService.queryTagByPage(req), "分页查询成功！");
    }

    @GetMapping("/get-one/{tageId}")
    @ApiOperation("根据id查询标签")
    public CommonResponse<TagsQueryResp> getOneById(@PathVariable("tageId") @NotNull(message = "id不能为空") Long tageId) {
        return CommonResponse.success(CopyUtil.copy(tagsService.getById(tageId), TagsQueryResp.class), "查询成功！");
    }

    @DeleteMapping("/delete/{tagId}")
    @ApiOperation("根据id删除标签")
    public CommonResponse<Boolean> deleteTagById(@PathVariable @NotNull(message = "id不能为空！") Long tagId) {
        return CommonResponse.success(tagsService.removeById(tagId), "删除成功！");
    }

}
