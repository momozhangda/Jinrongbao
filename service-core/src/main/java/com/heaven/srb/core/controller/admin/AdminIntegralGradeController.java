package com.heaven.srb.core.controller.admin;

import com.heaven.common.exception.BusinessException;
import com.heaven.common.result.R;
import com.heaven.common.result.ResponseEnum;
import com.heaven.srb.core.pojo.entity.IntegralGrade;
import com.heaven.srb.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author Heaven
 * @since 2021-12-08
 */

@CrossOrigin
@Api(tags = "积分等级管理")
@RestController
@RequestMapping("/admin/core/integralGrade")
@Slf4j
public class AdminIntegralGradeController {
    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public R listAll(){
        List<IntegralGrade> list = integralGradeService.list();
        return R.ok().data("list",list).message("获取列表成功");
    }

    @ApiOperation("根据Id删除数据记录")
    @DeleteMapping("/remove/{id}")
    public R deleteIntegralGrade(@PathVariable int id){
        boolean b = integralGradeService.removeById(id);
        if (b){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(@RequestBody IntegralGrade integralGrade){

        if (integralGrade.getBorrowAmount() == null){
            throw new BusinessException(ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        }

        boolean b = integralGradeService.save(integralGrade);

        if (b){
            return R.ok().message("新增成功");
        }else {
            return R.error().message("新增失败");
        }
    }

    @ApiOperation("通过ID查询")
    @GetMapping("/getid/{id}")
    public R getById(@PathVariable Long id) {
        IntegralGrade byId = integralGradeService.getById(id);
        if (byId != null) {
            return R.ok().data("record", byId);
        } else {
            return R.error().message("数据获取失败");
        }
    }

    @ApiOperation("更新积分列表")
    @PutMapping("/update")
    public R updateById(@RequestBody IntegralGrade integralGrade){
        boolean b = integralGradeService.updateById(integralGrade);
        if (b){
            return R.ok().message("修改成功");
        }else {
            return R.error().message("修改失败");
        }
    }
}

