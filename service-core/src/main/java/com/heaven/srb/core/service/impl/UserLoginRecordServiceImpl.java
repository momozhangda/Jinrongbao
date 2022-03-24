package com.heaven.srb.core.service.impl;

import com.heaven.srb.core.pojo.entity.UserLoginRecord;
import com.heaven.srb.core.mapper.UserLoginRecordMapper;
import com.heaven.srb.core.service.UserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author Heaven
 * @since 2021-12-08
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

}
