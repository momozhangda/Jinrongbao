package com.heaven.srb.core.service.impl;

import com.heaven.srb.core.pojo.entity.UserAccount;
import com.heaven.srb.core.mapper.UserAccountMapper;
import com.heaven.srb.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author Heaven
 * @since 2021-12-08
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
