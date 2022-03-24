package com.heaven.srb.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heaven.srb.core.listener.ExcelDictDTOListener;
import com.heaven.srb.core.pojo.dto.ExcelDicDTO;
import com.heaven.srb.core.pojo.entity.Dict;
import com.heaven.srb.core.mapper.DictMapper;
import com.heaven.srb.core.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author Heaven
 * @since 2021-12-08
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private RedisTemplate redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importData(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelDicDTO.class, new ExcelDictDTOListener(baseMapper)).sheet().doRead();
    }

    @Override
    public List<ExcelDicDTO> listDictData() {
        List<Dict> list = baseMapper.selectList(null);
        ArrayList<ExcelDicDTO> excelDicDTOArrayList = new ArrayList<>(list.size());
        list.forEach(dict -> {
            ExcelDicDTO excelDicDTO = new ExcelDicDTO();
            BeanUtils.copyProperties(dict,excelDicDTO);
            excelDicDTOArrayList.add(excelDicDTO);
        });
        return excelDicDTOArrayList;
    }

    @Override
    public List<Dict> listByParentId(Long parentId) {

        try {
            List<Dict> dictlist = (List<Dict>) redisTemplate.opsForValue().get("srb:core:dictlist"+parentId);
            if (dictlist != null){
                return dictlist;
            }
        } catch (Exception e) {
            log.error("redis服务器异常："+ ExceptionUtils.getStackTrace(e));
        }
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parentId);
        List<Dict> dictList = baseMapper.selectList(wrapper);
        dictList.forEach(dict -> {
            Boolean hasChildren = this.hasChildren(dict.getId());
            dict.setHasChildren(hasChildren);
        });

        redisTemplate.opsForValue().set("srb:core:dictlist"+parentId,dictList,5, TimeUnit.MINUTES);

        return dictList;
    }


    private Boolean hasChildren(Long id){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        if (count >= 1){
            return true;
        }else {
            return false;

        }    }
}
