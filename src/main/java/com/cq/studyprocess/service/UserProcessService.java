package com.cq.studyprocess.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cq.studyprocess.domain.UserProcess;
import com.cq.studyprocess.req.userProcess.UserProcessSaveReq;

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
     * @param req 要求事情
     */
    void saveUserProcess(UserProcessSaveReq req, HttpSession session);


}
