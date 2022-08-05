package com.cq.studyprocess.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cq.studyprocess.constants.UserConstant;
import com.cq.studyprocess.domain.User;
import com.cq.studyprocess.exception.BusinessException;
import com.cq.studyprocess.mapper.UserMapper;
import com.cq.studyprocess.req.UserRegisterReq;
import com.cq.studyprocess.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cq.studyprocess.utils.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import static com.cq.studyprocess.constants.UserConstant.SALT;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 程崎
 * @since 2022-08-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void register(UserRegisterReq req) {

        int count = this.count(Wrappers.lambdaQuery(User.class).eq(User::getEmail, req.getEmail()));
        if (count != 0) {
            throw new BusinessException("该邮箱已注册");
        }

        User user = CopyUtil.copy(req, User.class);
        user.setAvatar(UserConstant.DEFAULT_AVATAR);
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword() + SALT).getBytes()));
        this.save(user);
    }
}
