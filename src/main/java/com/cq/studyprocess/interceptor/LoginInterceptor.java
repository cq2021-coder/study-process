package com.cq.studyprocess.interceptor;

import com.cq.studyprocess.annotations.AdminRole;
import com.cq.studyprocess.domain.User;
import com.cq.studyprocess.exception.BusinessCode;
import com.cq.studyprocess.exception.BusinessException;
import com.cq.studyprocess.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.cq.studyprocess.constants.UserConstant.ADMIN_ROLE;
import static com.cq.studyprocess.constants.UserConstant.SESSION_KEYWORDS;

/**
 * 登录拦截器
 *
 * @author 程崎
 * @since 2022/08/06
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        log.info("-----------start interceptor------------");

        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute(SESSION_KEYWORDS);
        if (ObjectUtils.isEmpty(userSession)) {
            throw new BusinessException(BusinessCode.USER_MESSAGE_ERROR, "session中没有此类用户！");
        }
        log.info("user from session is:{}", userSession);

        //当handlerMethod.hasMethodAnnotation(AdminRole.class)为true时执行鉴权操作
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.hasMethodAnnotation(AdminRole.class)) {
            User userById = userMapper.selectById(userSession.getUserId());
            if (ObjectUtils.isEmpty(userById)) {
                throw new BusinessException(BusinessCode.USER_MESSAGE_ERROR, "数据库中没有此类用户！");
            }
            if (!userById.getRoles().equals(ADMIN_ROLE)) {
                throw new BusinessException(BusinessCode.USER_MESSAGE_ERROR, "用户无权限！");
            }
        }
        return true;
    }
}
