package com.qs.realm;

import com.qs.entity.UserInfo;
import com.qs.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class CustomRealm extends AuthorizingRealm {
    @Resource
    private UserInfoService userInfoService;


    /**
     * 认证 (用户登录)
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //用户名
        String userName = (String) token.getPrincipal();
        //e10adc3949ba59abbe56e057f20f883e
        String password = new String((char[]) token.getCredentials());
        System.out.println("--userName=" + userName + ",password=" + password);

        UserInfo userInfo = userInfoService.getUserInfoByUserName(userName);
        if (userInfo == null) {
            return null;
        }

        //定义salt，用来给用户名加密，加密后和数据库中保存的密码比较，验证用户名和密码相同后，返回
        ByteSource salt = ByteSource.Util.bytes(userInfo.getSalt());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), salt, this.getName());
        return info;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println(principalCollection.getPrimaryPrincipal());
        return null;
    }
}
