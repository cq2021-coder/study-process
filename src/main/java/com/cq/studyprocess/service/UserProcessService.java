package com.cq.studyprocess.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cq.studyprocess.domain.UserProcess;
import com.cq.studyprocess.req.PageReq;
import com.cq.studyprocess.req.userProcess.UserProcessQueryReq;
import com.cq.studyprocess.req.userProcess.UserProcessSaveReq;
import com.cq.studyprocess.req.userProcess.UserProcessUpdateReq;
import com.cq.studyprocess.resp.PageResp;
import com.cq.studyprocess.resp.UserProcessQueryResp;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 学习进度表 服务类
 * </p>
 *
 * @author 程崎
 * @since 2022-08-08
 */
public interface UserProcessService extends IService<UserProcess> {

    /**
     * 保存学习进度
     *
     * @param req     要求事情
     * @param session 会话
     */
    void saveUserProcess(UserProcessSaveReq req, HttpSession session);

    /**
     * 更新用户学习进度
     *
     * @param req     要求事情
     * @param session 会话
     */
    void updateUserProcess(UserProcessUpdateReq req, HttpSession session);

    /**
     * 分页查询
     *
     * @param req 要求事情
     * @return {@link PageResp}<{@link UserProcessQueryResp}>
     */
    PageResp<UserProcessQueryResp> queryByPage(UserProcessQueryReq req);

    /**
     * 根据session查询当前用户学习进度
     *
     * @param session 会话
     * @param req     要求事情
     * @return {@link PageResp}<{@link UserProcessQueryResp}>
     */
    PageResp<UserProcessQueryResp> queryBySession(PageReq req, HttpSession session);


}
