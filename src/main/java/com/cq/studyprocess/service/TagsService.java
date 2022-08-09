package com.cq.studyprocess.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cq.studyprocess.domain.Tags;
import com.cq.studyprocess.req.tag.TagsQueryPageReq;
import com.cq.studyprocess.req.tag.TagsSaveOrUpdateReq;
import com.cq.studyprocess.resp.PageResp;
import com.cq.studyprocess.resp.TagsQueryResp;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author 程崎
 * @since 2022-08-08
 */
public interface TagsService extends IService<Tags> {

    /**
     * 保存或更新标签
     *
     * @param req 要求事情
     */
    void saveOrUpdateTag(TagsSaveOrUpdateReq req);

    /**
     * 分页查询标签
     *
     * @param req 分页请求
     * @return {@link Page}<{@link Tags}>
     */
    PageResp<TagsQueryResp> queryTagByPage(TagsQueryPageReq req);


}
