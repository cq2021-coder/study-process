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
 * 进度和标签的中间表id
 * </p>
 *
 * @author 程崎
 * @since 2022-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProcessTag对象", description="进度和标签的中间表id")
public class ProcessTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "进度和标签的中间表id")
    @TableId
    private Long recordId;

    @ApiModelProperty(value = "进度表id")
    private Long processId;

    @ApiModelProperty(value = "标签id")
    private Long tagId;

    @ApiModelProperty(value = "标签内容")
    private String tagName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除字段：0未删除，1已删除")
    private Integer isDelete;


}
