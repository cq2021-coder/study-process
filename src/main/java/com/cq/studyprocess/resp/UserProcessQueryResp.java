package com.cq.studyprocess.resp;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="UserProcessQueryResp对象", description="学习进度表")
public class UserProcessQueryResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "进度id")
    @TableId
    private Long processId;

    @ApiModelProperty(value = "今日学习内容")
    private String todayContent;

    @ApiModelProperty(value = "目标内容")
    private String targetContent;

    @ApiModelProperty(value = "用户信息")
    private UserQueryResp user;

    @ApiModelProperty(value = "标签")
    private List<String> tagNames;

    @ApiModelProperty(value = "预计完成时间")
    private LocalDateTime expectedTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
