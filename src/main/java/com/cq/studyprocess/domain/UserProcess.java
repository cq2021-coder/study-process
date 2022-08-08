package com.cq.studyprocess.domain;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class UserProcess implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "进度id")
    @TableId
    private Long processId;

    @ApiModelProperty(value = "今日学习内容")
    private String todayContent;

    @ApiModelProperty(value = "目标内容")
    private String targetContent;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "预计完成时间")
    private LocalDateTime expectedTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除字段：0未删除，1已删除")
    private Integer isDelete;


}
