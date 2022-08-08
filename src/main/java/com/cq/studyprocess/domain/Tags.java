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
 * 标签表
 * </p>
 *
 * @author 程崎
 * @since 2022-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Tags对象", description="标签表")
public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId
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
