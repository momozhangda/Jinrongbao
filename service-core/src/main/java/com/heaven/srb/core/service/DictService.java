package com.heaven.srb.core.service;

import com.heaven.srb.core.pojo.dto.ExcelDicDTO;
import com.heaven.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author Heaven
 * @since 2021-12-08
 */
public interface DictService extends IService<Dict> {

    void importData(InputStream inputStream);

    List<ExcelDicDTO> listDictData();

    List<Dict> listByParentId(Long parentId);
}
