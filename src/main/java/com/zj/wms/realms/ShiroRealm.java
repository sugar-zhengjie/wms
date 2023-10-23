package com.zj.wms.realms;

import com.zj.wms.pojo.Menu;
import com.zj.wms.pojo.Role;
import com.zj.wms.pojo.User;
import com.zj.wms.service.MenuService;
import com.zj.wms.service.RoleService;
import com.zj.wms.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * realm实现类,用于实现具体的验证和授权方法
 * @author Bean
 *
 */
public class ShiroRealm extends AuthorizingRealm {

    private UserService userService;
    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    private RoleService roleService;
    @Autowired
    private void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    private MenuService menuService;
    @Autowired
    private void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
    /**
     * 方面用于加密 参数：AuthenticationToken是从表单穿过来封装好的对象
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获得从表单传过来的用户名和密码
        //从界面传来的用户名
        String account = token.getPrincipal().toString();
        String password = new String((char[]  )token.getCredentials());
        // 从数据库查看是否存在用户
        User user=userService.selectUser(account);
        // 如果用户不存在，抛此异常
        if (!account.equalsIgnoreCase(user.getAccount())) {
            throw new UnknownAccountException("无此用户名！");
        }
        // 当前realm对象的名称，调用分类的getName()
        String realmName = this.getName();
        // 盐值用于加密，这里用的khwms---自定义
        ByteSource credentialsSalt = ByteSource.Util.bytes("wms");
        return new SimpleAuthenticationInfo(account, user.getPassword(), credentialsSalt, realmName);
    }

    // 用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // User user=(User)
        // principals.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        // System.out.println("在MyShiroRealm中AuthorizationInfo（授权）方法中从session中获取的user对象:"+user);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Set<String> roles=new HashSet<String>();
        // 从PrincipalCollection中获得用户信息
        String account =(String) SecurityUtils.getSubject().getPrincipal();
        // 根据用户名来查询数据库赋予用户角色,权限（查数据库）
        Role role=roleService.getRoleByAccount(account);
        roles.add(role.getName());
        List<Menu> permissionsByAccount=menuService.getPermissionByAccount(account);
        for(Menu menu:permissionsByAccount){
            info.addStringPermission(menu.getName());
        }
        info.setRoles(roles);
        return info;
    }
}