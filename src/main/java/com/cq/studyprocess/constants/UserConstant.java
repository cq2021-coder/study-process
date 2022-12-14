package com.cq.studyprocess.constants;

/**
 * 用户常量
 *
 * @author 程崎
 * @since 2022/08/05
 */
public interface UserConstant {
    /**
     * 默认头像
     */
    String DEFAULT_AVATAR = "https://portrait.gitee.com/uploads/avatars/user/2860/8580593_cq2021_1641226315.png!avatar200";

    String SALT = "ahjsdbaks2ui4ynkj*(^*&";

    /**
     * 会话关键字
     */
    String SESSION_KEYWORDS = "current-user";

    /**
     * 普通用户
     */
    Integer COMMON_ROLE = 0;

    /**
     * 管理员
     */
    Integer ADMIN_ROLE = 1;
}
