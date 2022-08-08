package com.cq.studyprocess.service.impl;

import com.cq.studyprocess.domain.Tags;
import com.cq.studyprocess.mapper.TagsMapper;
import com.cq.studyprocess.service.TagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
