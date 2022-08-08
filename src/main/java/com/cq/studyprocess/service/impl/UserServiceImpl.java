package com.cq.studyprocess.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cq.studyprocess.constants.UserConstant;
import com.cq.studyprocess.domain.User;
import com.cq.studyprocess.exception.BusinessCode;
import com.cq.studyprocess.exception.BusinessException;
import com.cq.studyprocess.mapper.UserMapper;
import com.cq.studyprocess.req.UserLoginReq;
import com.cq.studyprocess.req.UserQueryAllReq;
import com.cq.studyprocess.req.UserRegisterReq;
import com.cq.studyprocess.req.UserUpdateReq;
import com.cq.studyprocess.resp.PageResp;
import com.cq.studyprocess.resp.UserQueryResp;
import com.cq.studyprocess.service.CommonService;
import com.cq.studyprocess.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cq.studyprocess.utils.CopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.cq.studyprocess.constants.UserConstant.SALT;
import static com.cq.studyprocess.constants.UserConstant.SESSION_KEYWORDS;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 程崎
 * @since 2022-08-05
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CommonService commonService;

    @Override
    public void register(UserRegisterReq req) {

        int count = userMapper.countByEmail(req.getEmail());
        User user = CopyUtil.copy(req, User.class);
        user.setAvatar(UserConstant.DEFAULT_AVATAR);
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword() + SALT).getBytes()));

        if (count!=0) {
            userMapper.updateUser(user);
        }else {
            this.save(user);
        }
    }

    @Override
    public UserQueryResp login(UserLoginReq req, HttpSession session) {
        //1、根据email去查询一个用户，如果没有的话，那么说明此用户不存在
        User userDb = this.getOne(Wrappers.lambdaQuery(User.class).eq(User::getEmail, req.getEmail()));
        if (ObjectUtils.isEmpty(userDb)) {
            throw new BusinessException(BusinessCode.LOGIN_ERROR,BusinessCode.LOGIN_ERROR.getMessage());
        }
        String reqPassword = DigestUtils.md5DigestAsHex((req.getPassword() + SALT).getBytes());
        //2、对比传入的密码是否正确
        if (!reqPassword.equals(userDb.getPassword())) {
            throw new BusinessException(BusinessCode.LOGIN_ERROR,BusinessCode.LOGIN_ERROR.getMessage());
        }

        User sessionUser = new User();
        sessionUser.setUserId(userDb.getUserId());
        session.setAttribute(SESSION_KEYWORDS, sessionUser);

        return CopyUtil.copy(userDb, UserQueryResp.class);
    }

    @Override
    public PageResp<UserQueryResp> queryAll(UserQueryAllReq req, HttpSession session) {

        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class)
                .like(!ObjectUtils.isEmpty(req.getUsername()), User::getUsername, req.getUsername())
                .eq(!ObjectUtils.isEmpty(req.getEmail()), User::getEmail, req.getEmail())
                .eq(!ObjectUtils.isEmpty(req.getRoles()), User::getRoles, req.getRoles());

        Page<User> page = this.page(new Page<>(req.getPage(), req.getSize()), queryWrapper);
        log.info("the result page is :{}", page);
        return new PageResp<>(
                CopyUtil.copyList(
                        page.getRecords(), UserQueryResp.class),
                page.getSize()
        );

    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute(SESSION_KEYWORDS);
    }

    @Override
    public UserQueryResp queryById(Long id) {
        return CopyUtil.copy(this.getById(id), UserQueryResp.class);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        List<User> userList = this.listByIds(ids);
        userList.forEach(user -> commonService.deleteFile(user.getAvatar()));
        this.removeByIds(ids);
    }

    @Override
    public void updateUser(UserUpdateReq req) {
        User currentUser = this.getById(req.getUserId());
        if (!ObjectUtils.isEmpty(req.getEmail())) {

            //不一致代表用户希望修改邮箱
            if(!currentUser.getEmail().equals(req.getEmail())) {
                int count = this.count(Wrappers.lambdaQuery(User.class).eq(User::getEmail, req.getEmail()));
                //不为0就是此邮箱存在
                if (count != 0) {
                    throw new BusinessException(BusinessCode.PARAMS_ERROR, "邮箱已被注册，不能修改！");
                }
            }
        }
        if (!ObjectUtils.isEmpty(req.getAvatar()) && !currentUser.getAvatar().equals(req.getAvatar())) {
            commonService.deleteFile(currentUser.getAvatar());
        }
        if (!ObjectUtils.isEmpty(req.getPassword())) {
            req.setPassword(DigestUtils.md5DigestAsHex((req.getPassword()+SALT).getBytes()));
        }
        this.updateById(CopyUtil.copy(req, User.class));
    }

}
