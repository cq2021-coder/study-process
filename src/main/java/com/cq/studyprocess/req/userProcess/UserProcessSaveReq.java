package com.cq.studyprocess.req.userProcess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
public class UserProcessSaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "今日学习内容")
    @NotBlank(message = "今日学习内容不能为空")
    private String todayContent;

    @ApiModelProperty(value = "目标内容")
    @NotBlank(message = "目标内容不能为空")
    private String targetContent;

    @ApiModelProperty(value = "预计完成时间")
    @NotNull(message = "预计完成时间不能为空")
    private LocalDateTime expectedTime;

    @ApiModelProperty(value = "标签id")
    private List<Long> tagIds;


}
