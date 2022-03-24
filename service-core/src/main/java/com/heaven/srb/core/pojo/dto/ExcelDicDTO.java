package com.heaven.srb.core.pojo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelDicDTO {
    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("parentId")
    private Long parentId;

    @ExcelProperty("name")
    private String name;

    @ExcelProperty("value")
    private Integer value;

    @ExcelProperty("dictCode")
    private String dictCode;
}
