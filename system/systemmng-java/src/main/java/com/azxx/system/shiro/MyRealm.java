package com.azxx.system.shiro;

import com.azxx.system.entity.User;
import com.azxx.system.service.ShiroUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Created by smile on 2018/4/6.
 */
public class MyRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ShiroUserService userService;
    /**
     * 为当前登陆成功的用户授予权限和角色，已经登陆成功了
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 根据用户配置用户与权限
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        String username = (String) principals.getPrimaryPrincipal(); //获取用户名
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<String> roles = userService.getRoles(username);
        if(roles!=null&&roles.size()>0){
            authorizationInfo.setRoles((Set<String>) roles);
            authorizationInfo.setStringPermissions((Set<String>) userService.getResources(roles));
        }
        return authorizationInfo;
    }

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userName = token.getUsername();

        logger.info("请求用户名："+userName);
        // 清空权限缓存,保证每次登陆都重新同步缓存至最新
        SimplePrincipalCollection key = null;
        Cache<Object, AuthorizationInfo> cache = super.getAuthorizationCache();
        for (Object k : cache.keys()) {
            if (k.toString().equals(userName)) {
                key = (SimplePrincipalCollection) k;
                break;
            }
        }
        if (key != null) {
            cache.remove(key);
        }
//        logger.info("doGetAuthenticationInfoAuthenticationToken............username=" + userName);
        if (userName != null && !"".equals(userName)){
            User user = null;
            try {
                user = userService.getUser(userName);
                logger.info("username=" + userName + ";load=" + (user == null ? "false" : "true"));
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            if(user!=null && StringUtils.isNotBlank(user.getUserName())){
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),getName());
                SecurityUtils.getSubject().getSession()
                        .setAttribute("c_user", user);
                return authenticationInfo;
            }else {
                throw new UnknownAccountException("该账号未注册,请尝试其他账号！");
            }
        }
        return null;
    }
}