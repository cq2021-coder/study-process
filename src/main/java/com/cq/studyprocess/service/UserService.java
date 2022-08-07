package com.cq.studyprocess.service;

import com.cq.studyprocess.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cq.studyprocess.req.UserLoginReq;
import com.cq.studyprocess.req.UserQueryAllReq;
import com.cq.studyprocess.req.UserRegisterReq;
import com.cq.studyprocess.resp.PageResp;
import com.cq.studyprocess.resp.UserQueryResp;

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
     * @return {@link UserQueryResp}
     */
    UserQueryResp login(UserLoginReq req, HttpSession session);

    /**
     * 查询所有
     * 查询所有用户
     *
     * @param session 会话
     * @param req     要求事情
     * @return {@link List}<{@link User}>
     */
    PageResp<UserQueryResp> queryAll(UserQueryAllReq req, HttpSession session);

    /**
     * 注销
     *
     * @param session 会话
     */
    void logout(HttpSession session);

    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link UserQueryResp}
     */
    UserQueryResp queryById(Long id);

    /**
     * 删除通过id
     *
     * @param ids id
     */
    void deleteByIds(List<Long> ids);

}
