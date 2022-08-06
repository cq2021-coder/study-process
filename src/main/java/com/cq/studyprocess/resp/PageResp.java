package com.cq.studyprocess.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;

/**
 * 分页响应
 *
 * @author 程崎
 * @since 2022/08/06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResp<T> implements Serializable {

    private static final long serialVersionUID = 12348916545694L;

    private List<T> content;

    private long total;

}
