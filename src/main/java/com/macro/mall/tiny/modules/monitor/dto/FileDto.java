package com.macro.mall.tiny.modules.monitor.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class FileDto {
    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "项目名")
    private String projectName;
}