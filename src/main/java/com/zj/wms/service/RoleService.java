package com.zj.wms.service;

import com.zj.wms.mapper.RoleMapper;
import com.zj.wms.pojo.Role;
import com.zj.wms.pojo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {

    @Autowired
    RoleMapper rm;

    //根据用户名查询角色
    public Role getRoleByAccount(String account) {
        return rm.getRoleByAccount(account);
    }

    //查询所有角色
    public List<Role> getAllRole() { return rm.getAllRole(); }

    //根据关键词查询角色
    public List<Role> getRolesByKeywords(String keywords){ return rm.getRolesByKeywords(keywords);};

    //根据id删除角色
    public void deleteByRoleId(String role_id){ rm.deleteByRoleId(role_id);};

    //根据id查找角色
    public Role getRoleByRoleId(String role_id){return rm.getRoleByRoleId(role_id);};

    //保存修改的角色信息
    public void saveEditRole(Role role){ rm.saveEditRole(role);};

    //获得最大角色排序+1
    public int getNewDisplayOrder() {
        Integer maxDisplayOrder = rm.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }
    //新增角色
    public void addRole(Role role){rm.addRole(role);}

    public int getNewDisplayOrderFromUserRole() {
        Integer maxDisplayOrder = rm.selectMaxDisplayOrderFromUserRole();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addUserRole(UserRole ur){rm.addUserRole(ur);};
}
