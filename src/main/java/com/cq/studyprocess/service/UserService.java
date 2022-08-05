package com.cq.studyprocess.service;

import com.cq.studyprocess.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cq.studyprocess.req.UserRegisterReq;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 程崎
 * @since 2022-08-05
 */
public interface UserService extends IService<User> {

    /**
     * 注册
     *
     * @param req 要求事情
     */
    void register(UserRegisterReq req);

}
