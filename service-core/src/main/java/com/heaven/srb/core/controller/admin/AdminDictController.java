package com.heaven.srb.core.controller.admin;

import com.alibaba.excel.EasyExcel;
import com.heaven.common.exception.BusinessException;
import com.heaven.common.result.R;
import com.heaven.common.result.ResponseEnum;
import com.heaven.srb.core.pojo.dto.ExcelDicDTO;
import com.heaven.srb.core.pojo.entity.Dict;
import com.heaven.srb.core.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Api(tags = "数据字典管理")
@RestController
@RequestMapping("/admin/core/dict")
@CrossOrigin
@Slf4j
public class AdminDictController {

    @Resource
    DictService dictService;

    @ApiOperation("Excel数据的导入")
    @PostMapping("/import")
    public R batchImport(@RequestPart("file") MultipartFile file){
        try{
            log.info("文件开始上传");
            InputStream inputStream=file.getInputStream();
            dictService.importData(inputStream);
            return R.ok().message("数据字典数据批量导入成功");
        }catch (Exception e){
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR,e);
        }


    }

    @ApiOperation("Excel数据的导出")
    @GetMapping("/export")
    public R export(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("mydict", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(),ExcelDicDTO.class).sheet("数据字典").doWrite(dictService.listDictData());
            return R.ok().message("Excel数据导出成功");
        } catch (Exception  e) {
            e.printStackTrace();
            return R.error().message("Excel数据导出失败");
        }
    }

    @GetMapping("/listByParentId/{parentId}")
    public R listByParentId(@PathVariable Long parentId){
        List<Dict> dictList = dictService.listByParentId(parentId);
        return R.ok().data("list",dictList);
    }

}
