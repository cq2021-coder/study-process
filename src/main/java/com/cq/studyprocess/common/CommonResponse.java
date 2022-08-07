package com.cq.studyprocess.common;

import com.cq.studyprocess.exception.BusinessCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @author 程崎
 * @since 2022/08/05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> implements Serializable {
    private static final long serialVersionUID = 112345679815613L;

    private boolean success;

    private T data;

    private String message;

    /**
     * 成功
     *
     * @return {@link CommonResponse}<{@link T}>
     */
    public static <T> CommonResponse<T> success(T data, String message) {
        return new CommonResponse<>(true, data, message);
    }

    /**
     * 成功
     *
     * @return {@link CommonResponse}<{@link T}>
     */
    public static <T> CommonResponse<T> success(String message) {
        return new CommonResponse<>(true, null, message);
    }

    /**
     * 成功
     *
     * @return {@link CommonResponse}<{@link T}>
     */
    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(true, null, "");
    }

    public static CommonResponse<Object> error(BusinessCode code, String message) {
        return new CommonResponse<>(false, code.getMessage(), message);
    }


}
