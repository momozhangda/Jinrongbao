package com.atheaven.easyexcel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelStudentDTO {
    private String name;
    private Date birthday;
    private Double salary;
}
