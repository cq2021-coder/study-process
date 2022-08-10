package com.cq.studyprocess.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cq.studyprocess.domain.Tags;
import com.cq.studyprocess.mapper.TagsMapper;
import com.cq.studyprocess.req.tag.TagsQueryPageReq;
import com.cq.studyprocess.req.tag.TagsSaveOrUpdateReq;
import com.cq.studyprocess.resp.PageResp;
import com.cq.studyprocess.resp.TagsQueryResp;
import com.cq.studyprocess.service.TagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cq.studyprocess.utils.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author 程崎
 * @since 2022-08-08
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {

    @Override
    public void saveOrUpdateTag(TagsSaveOrUpdateReq req) {
        Tags tags = CopyUtil.copy(req, Tags.class);
        if (ObjectUtils.isEmpty(req.getTagId())) {
            this.save(tags);
            return;
        }
        this.updateById(tags);
    }

    @Override
    public PageResp<TagsQueryResp> queryTagByPage(TagsQueryPageReq req) {
        Page<Tags> tagsPage = this.page(
                new Page<>(req.getPage(), req.getSize()),
                Wrappers.lambdaQuery(Tags.class)
                        .like(
                                !ObjectUtils.isEmpty(req.getTagName()),
                                Tags::getTagName, req.getTagName()
                        )
                        .orderByAsc(Tags::getTagName)
                        .orderByDesc(Tags::getUpdateTime)
        );
        PageResp<TagsQueryResp> pageResp = new PageResp<>();
        pageResp.setContent(CopyUtil.copyList(tagsPage.getRecords(), TagsQueryResp.class));
        pageResp.setTotal(tagsPage.getTotal());
        return pageResp;
    }
}
