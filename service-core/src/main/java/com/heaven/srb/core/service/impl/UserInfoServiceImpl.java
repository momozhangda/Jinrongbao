package com.heaven.srb.core.service.impl;

import com.heaven.srb.core.pojo.entity.UserInfo;
import com.heaven.srb.core.mapper.UserInfoMapper;
import com.heaven.srb.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author Heaven
 * @since 2021-12-08
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
