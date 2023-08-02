package com.macro.mall.tiny.modules.monitor.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.monitor.dto.FileDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Api(tags = "FileController")
@Tag(name = "文件处理", description = "文件处理")
@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    @ApiOperation("根据文件名获取文件")
    @PostMapping("/get")
    public ResponseEntity<CommonResult<String>> getFileContent(@RequestBody FileDto fileDto) {
        String fileName = fileDto.getFileName();
        String projectName = fileDto.getProjectName();
        try {
            // 获取文件的路径
            Path filePath = Paths.get(ResourceUtils.getFile("classpath:" + "webdist/" + projectName + "/" + fileName).toURI());

            // 读取文件内容
            String fileContent = new String(Files.readAllBytes(filePath));

            // 返回文件内容给前端
            return CommonResult.success(fileContent);
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常情况，返回错误信息给前端
            return CommonResult.failed("Failed to read file: " + fileName);
        }
    }
}
