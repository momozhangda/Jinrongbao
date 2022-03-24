package com.heaven.srb.oss.controller.api;


import com.heaven.common.result.R;
import com.heaven.srb.oss.service.FileService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Api(tags = "阿里云文件管理")
@RestController
@RequestMapping("/api/oss/file")
@CrossOrigin
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("upload")
    public R upload(MultipartFile file, String module){
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileName = file.getOriginalFilename();
        String url = fileService.upLoad(inputStream, module, fileName);
        return R.ok().message("文件上传成功").data("url",url);
    };
}
