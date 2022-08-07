package com.cq.studyprocess.mapper;

import com.cq.studyprocess.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 程崎
 * @since 2022-08-06
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过电子邮件数
     *
     * @param email 电子邮件
     * @return int
     */
    int countByEmail(@Param("email") String email);

    /**
     * 插入用户
     *
     * @param user 用户
     */
    void updateUser(@Param("user") User user);

}
