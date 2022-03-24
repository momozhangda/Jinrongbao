package com.heaven.srb.core.mapper;

import com.heaven.srb.core.pojo.dto.ExcelDicDTO;
import com.heaven.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author Heaven
 * @since 2021-12-08
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(List<ExcelDicDTO> list);
}
