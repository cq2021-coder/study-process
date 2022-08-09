package com.cq.studyprocess.req.tag;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

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
@ApiModel(value="TagsSaveOrUpdateReq对象", description="标签表")
public class TagsSaveOrUpdateReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标签id")
    @TableId
    private Long tagId;

    @ApiModelProperty(value = "标签内容")
    @NotBlank(message = "标签名不能为空！")
    private String tagName;


}
