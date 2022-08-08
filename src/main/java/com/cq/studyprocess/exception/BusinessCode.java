package com.cq.studyprocess.exception;

/**
 * 业务代码
 *
 * @author 程崎
 * @since 2022/08/07
 */
public enum BusinessCode {
    /**
     * 各类枚举名称
     */
    PARAMS_ERROR("参数错误！"),
    USER_MESSAGE_ERROR("用户信息错误"),
    LOGIN_ERROR("账号或密码错误！"),
    FILE_ERROR("文件异常！"),
    ;
    private final String message;

    public String getMessage() {
        return message;
    }

    BusinessCode(String message) {
        this.message = message;
    }
}
