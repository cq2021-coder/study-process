package com.cq.studyprocess.req.userProcess;

import com.cq.studyprocess.req.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 学习进度表
 * </p>
 *
 * @author 程崎
 * @since 2022-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserProcess对象", description="学习进度表")
public class UserProcessQueryReq extends PageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关键字")
    private String keyWords;

    @ApiModelProperty(value = "标签id")
    private Long tagId;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;


}
