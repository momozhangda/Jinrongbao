package com.heaven.srb.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.heaven.srb.core.mapper.DictMapper;
import com.heaven.srb.core.pojo.dto.ExcelDicDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDicDTO> {

    private List<ExcelDicDTO> list = new ArrayList<>();

    private static final int SAVE_COUNT = 5;

    private DictMapper dictMapper;

    public ExcelDictDTOListener(DictMapper dictMapper){
        this.dictMapper=dictMapper;
    }

    @Override
    public void invoke(ExcelDicDTO excelDicDTO, AnalysisContext analysisContext) {
        list.add(excelDicDTO);
        if (list.size()>=5){
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }

    public void saveData(){
        dictMapper.insertBatch(list);
    }
}
