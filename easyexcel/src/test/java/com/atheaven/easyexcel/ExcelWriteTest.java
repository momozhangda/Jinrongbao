package com.atheaven.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.atheaven.easyexcel.dto.ExcelStudentDTO;
import com.atheaven.easyexcel.listener.ExcelStudentDTOListener;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelWriteTest {
    @Test
    public void simpleWriteTest(){
        String filename="D:/123/simpleWrite.xlsx";
        EasyExcel.write(filename,ExcelStudentDTO.class).sheet("第一个").doWrite(data());
    }
    public List<ExcelStudentDTO> data(){
        List<ExcelStudentDTO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExcelStudentDTO data =new ExcelStudentDTO();
            data.setName(i+"无敌");
            data.setBirthday(new Date());
            data.setSalary(i+0.56);
            list.add(data);
        }
        return list;
    }

    @Test
    public void simpleReadTest(){
        try {
            FileInputStream inputStream = new FileInputStream("C:\\Users\\邱怡成\\Desktop\\test1.xlsx");
            EasyExcel.read(inputStream,ExcelStudentDTO.class,new ExcelStudentDTOListener()).sheet().doRead();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
