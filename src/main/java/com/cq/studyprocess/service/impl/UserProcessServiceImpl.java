package com.cq.studyprocess.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cq.studyprocess.domain.ProcessTag;
import com.cq.studyprocess.domain.Tags;
import com.cq.studyprocess.domain.User;
import com.cq.studyprocess.domain.UserProcess;
import com.cq.studyprocess.exception.BusinessCode;
import com.cq.studyprocess.exception.BusinessException;
import com.cq.studyprocess.mapper.ProcessTagMapper;
import com.cq.studyprocess.mapper.TagsMapper;
import com.cq.studyprocess.mapper.UserProcessMapper;
import com.cq.studyprocess.req.userProcess.UserProcessSaveReq;
import com.cq.studyprocess.service.UserProcessService;
import com.cq.studyprocess.utils.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.cq.studyprocess.constants.UserConstant.SESSION_KEYWORDS;

/**
 * <p>
 * 学习进度表 服务实现类
 * </p>
 *
 * @author 程崎
 * @since 2022-08-08
 */
@Service
public class UserProcessServiceImpl extends ServiceImpl<UserProcessMapper, UserProcess> implements UserProcessService {

    @Resource
    private TagsMapper tagsMapper;

    @Resource
    private ProcessTagMapper processTagMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserProcess(UserProcessSaveReq req, HttpSession session) {
        User userSession = (User) session.getAttribute(SESSION_KEYWORDS);
        Tags tagsById;
        UserProcess userProcess = CopyUtil.copy(req, UserProcess.class);
        userProcess.setUserId(userSession.getUserId());
        this.save(userProcess);
        List<Long> tagIds = req.getTagIds();
        if (!ObjectUtils.isEmpty(tagIds)) {
            for (Long tagId : tagIds) {
                tagsById = tagsMapper.selectById(tagId);
                if (ObjectUtils.isEmpty(tagsById)) {
                    throw new BusinessException(BusinessCode.PARAMS_ERROR, "标签不存在！");
                }
                ProcessTag processTag = new ProcessTag();
                processTag.setTagId(tagId);
                processTag.setProcessId(userProcess.getProcessId());
                processTag.setTagName(tagsById.getTagName());
                processTagMapper.insert(processTag);
            }

        }
    }
}
