package com.cq.studyprocess.service;

import com.cq.studyprocess.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cq.studyprocess.req.PageReq;
import com.cq.studyprocess.req.UserLoginReq;
import com.cq.studyprocess.req.UserQueryAllReq;
import com.cq.studyprocess.req.UserRegisterReq;
import com.cq.studyprocess.resp.PageResp;
import com.cq.studyprocess.resp.UserQueryAllResp;

import javax.servlet.http.HttpSession;
import java.util.List;

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
     * @param req 请求
     */
    void register(UserRegisterReq req);

    /**
     * 登录
     *
     * @param req     请求
     * @param session 会话
     * @return {@link User}
     */
    User login(UserLoginReq req, HttpSession session);

    /**
     * 查询所有
     * 查询所有用户
     *
     * @param session 会话
     * @param req     要求事情
     * @return {@link List}<{@link User}>
     */
    PageResp<UserQueryAllResp> queryAll(UserQueryAllReq req, HttpSession session);

}
