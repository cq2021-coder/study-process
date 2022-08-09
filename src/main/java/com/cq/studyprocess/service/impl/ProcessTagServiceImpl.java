package com.cq.studyprocess.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cq.studyprocess.domain.ProcessTag;
import com.cq.studyprocess.mapper.ProcessTagMapper;
import com.cq.studyprocess.service.ProcessTagService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 进度和标签的中间表id 服务实现类
 * </p>
 *
 * @author 程崎
 * @since 2022-08-09
 */
@Service
public class ProcessTagServiceImpl extends ServiceImpl<ProcessTagMapper, ProcessTag> implements ProcessTagService {

}
