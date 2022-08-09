package com.cq.studyprocess.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页请求
 *
 * @author 程崎
 * @since 2022/08/06
 */
@Data
public class PageReq implements Serializable {

    private static final long serialVersionUID = 45619816541654L;

    /**
     * 第几页
     */
    @ApiModelProperty(value = "当前页")
    private int page = 1;

    /**
     * 一页有多少数据
     */
    @ApiModelProperty(value = "分页大小")
    private int size = 10;

}
