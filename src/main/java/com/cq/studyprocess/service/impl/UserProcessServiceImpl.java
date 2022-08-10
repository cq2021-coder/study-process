package com.cq.studyprocess.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cq.studyprocess.domain.ProcessTag;
import com.cq.studyprocess.domain.Tags;
import com.cq.studyprocess.domain.User;
import com.cq.studyprocess.domain.UserProcess;
import com.cq.studyprocess.exception.BusinessCode;
import com.cq.studyprocess.exception.BusinessException;
import com.cq.studyprocess.mapper.ProcessTagMapper;
import com.cq.studyprocess.mapper.TagsMapper;
import com.cq.studyprocess.mapper.UserMapper;
import com.cq.studyprocess.mapper.UserProcessMapper;
import com.cq.studyprocess.req.PageReq;
import com.cq.studyprocess.req.userProcess.UserProcessQueryReq;
import com.cq.studyprocess.req.userProcess.UserProcessSaveReq;
import com.cq.studyprocess.req.userProcess.UserProcessUpdateReq;
import com.cq.studyprocess.resp.PageResp;
import com.cq.studyprocess.resp.UserProcessQueryResp;
import com.cq.studyprocess.resp.UserQueryResp;
import com.cq.studyprocess.service.UserProcessService;
import com.cq.studyprocess.utils.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private UserMapper userMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserProcess(UserProcessSaveReq req, HttpSession session) {
        User userSession = (User) session.getAttribute(SESSION_KEYWORDS);
        UserProcess userProcess = CopyUtil.copy(req, UserProcess.class);
        userProcess.setUserId(userSession.getUserId());
        this.save(userProcess);
        List<Long> tagIds = req.getTagIds();
        if (!ObjectUtils.isEmpty(tagIds)) {
            insertProcessTags(userProcess, tagIds);

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserProcess(UserProcessUpdateReq req, HttpSession session) {
        User userSession = (User) session.getAttribute(SESSION_KEYWORDS);
        UserProcess userProcess = this.getById(req.getProcessId());
        if (ObjectUtils.isEmpty(userProcess)) {
            throw new BusinessException(BusinessCode.PARAMS_ERROR, "当前信息不存在！");
        }
        if (!userProcess.getUserId().equals(userSession.getUserId())) {
            throw new BusinessException(BusinessCode.AUTH_ERROR, "无法修改他人信息！");
        }
        this.updateById(CopyUtil.copy(req, UserProcess.class));
        List<Long> tagIds = req.getTagIds();
        if (!ObjectUtils.isEmpty(tagIds)) {
            processTagMapper.delete(
                    Wrappers
                            .lambdaQuery(ProcessTag.class)
                            .eq(ProcessTag::getProcessId, req.getProcessId())
            );
            insertProcessTags(userProcess, tagIds);
        }
    }

    @Override
    public PageResp<UserProcessQueryResp> queryByPage(UserProcessQueryReq req) {
        LambdaQueryWrapper<UserProcess> queryWrapper = Wrappers.lambdaQuery(UserProcess.class);

        //预期时间在某某范围内
        queryWrapper.between(
                !ObjectUtils.isEmpty(req.getStartTime()) && !ObjectUtils.isEmpty(req.getEndTime()),
                UserProcess::getExpectedTime,
                req.getStartTime(),
                req.getEndTime()
        );

        //region 根据关键字模糊查询
        if (!ObjectUtils.isEmpty(req.getKeyWords())) {
            queryWrapper.like(UserProcess::getTodayContent, req.getKeyWords())
                    .or().like(UserProcess::getTargetContent, req.getKeyWords());
        }
        //endregion

        //region 根据标签查询进度
        List<Long> processIds = getProcessId(req);
        //endregion

        queryWrapper.in(!ObjectUtils.isEmpty(processIds), UserProcess::getProcessId, processIds);

        //添加排序
        queryWrapper.orderByDesc(UserProcess::getUpdateTime);

        //分页查询
        Page<UserProcess> processPage = this.page(new Page<>(req.getPage(), req.getSize()), queryWrapper);

        return new PageResp<>(getUserAndTags(processPage), processPage.getTotal());
    }

    @Override
    public PageResp<UserProcessQueryResp> queryBySession(PageReq req, HttpSession session) {
        User userSession = (User) session.getAttribute(SESSION_KEYWORDS);
        Page<UserProcess> userProcessPage = this.page(
                new Page<>(req.getPage(), req.getSize()),
                Wrappers
                        .lambdaQuery(UserProcess.class)
                        .eq(UserProcess::getUserId, userSession.getUserId())
        );
        return new PageResp<>(getUserAndTags(userProcessPage), userProcessPage.getTotal());
    }

    private void insertProcessTags(UserProcess userProcess, List<Long> tagIds) {
        Tags tagsById;
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

    /**
     * 根据标签查询进度
     *
     * @param req 要求事情
     * @return {@link List}<{@link Long}>
     */
    private List<Long> getProcessId(UserProcessQueryReq req) {
        List<ProcessTag> processTagList;
        List<Long> processIds = new ArrayList<>();

        if (!ObjectUtils.isEmpty(req.getTagId())) {

            //根据标签id查询所有匹配的学习进度
            processTagList = processTagMapper.selectList(
                    Wrappers
                            .lambdaQuery(ProcessTag.class)
                            .eq(ProcessTag::getTagId, req.getTagId())
                            .select(ProcessTag::getProcessId)
            );

            //查询到的转换成学习进度id
            processIds = processTagList.stream().map(ProcessTag::getProcessId).collect(Collectors.toList());
        }
        return processIds;
    }

    /**
     * 返回用户和标签
     *
     * @param processPage 过程页面
     * @return {@link List}<{@link UserProcessQueryResp}>
     */
    private List<UserProcessQueryResp> getUserAndTags(Page<UserProcess> processPage) {
        return processPage.getRecords().stream().map(record -> {
            UserProcessQueryResp resp = CopyUtil.copy(record, UserProcessQueryResp.class);
            //设置user
            resp.setUser(CopyUtil.copy(userMapper.selectById(record.getUserId()), UserQueryResp.class));

            //设置标签
            resp.setTagNames(
                    processTagMapper.selectList(
                            Wrappers.lambdaQuery(ProcessTag.class)
                                    .eq(ProcessTag::getProcessId, record.getProcessId())
                                    .select(ProcessTag::getTagName)
                    ).stream().map(ProcessTag::getTagName).collect(Collectors.toList())
            );
            return resp;
        }).collect(Collectors.toList());
    }
}
