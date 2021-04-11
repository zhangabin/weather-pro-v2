package com.ruoyi.wechat.web.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.framework.web.service.UserDetailsServiceImpl;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.wechat.web.service.WeChatLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 企业微信登录Service实现类
 * @ClassName WeChatLoginServiceImpl
 * @Author 1129017402@qq.com
 * @Date 2021/3/3 0:07
 */
@Service
public class WeChatLoginServiceImpl implements WeChatLoginService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public String login(String userName) {
        if(StringUtils.isEmpty(userName)){
            throw new RuntimeException("userName can not be empty");
        }

        SysUser user = userService.selectUserByUserName(userName);

        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", user);
            throw new UsernameNotFoundException("登录用户：" + userName + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", userName);
            throw new BaseException("对不起，您的账号：" + userName + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", userName);
            throw new BaseException("对不起，您的账号：" + userName + " 已停用");
        }

        // 生成token
        return tokenService.createToken(new LoginUser(user, permissionService.getMenuPermission(user)));
    }
}
