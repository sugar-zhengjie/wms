package com.zj.wms.mapper;

import com.zj.wms.pojo.Menu;
import com.zj.wms.pojo.Role;
import com.zj.wms.pojo.UserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface RoleMapper {
    //角色---用户为一对一
    @Select("select r.* from system_users u,system_user_role ur,system_role r where u.user_id=ur.user_id and ur.role_id=r.role_id and account=#{account};")
    Role getRoleByAccount(String account);
    //查询所有角色
    @Select("select * from system_role where is_deleted=0")
    List<Role> getAllRole();
    //查询某个角色拥有的权限
    @Select("select m.* from system_role r,system_menu m,system_role_menu rm where r.role_id=rm.role_id and rm.menu_id=m.menu_id and r.role_id=#{role_id}")
    List<Menu> getMenuByRoleId(String role_id);
    //根据关键词查询角色
    @Select("select role_id,name,description from system_role where is_deleted=0 and name like '%${keywords}%'")
    List<Role> getRolesByKeywords(String keywords);
    //根据id删除角色
    @Update("update system_role set is_deleted=1 where role_id=#{role_id}")
    void deleteByRoleId(String role_id);
    //根据id查找角色
    @Select("select * from system_role where role_id=#{role_id}")
    Role getRoleByRoleId(String role_id);
    //保存修改的角色信息
    @Update("update system_role set name=#{name},description=#{description},last_updated_user_id=#{last_updated_user_id},last_updated_user_name=#{last_updated_user_name},last_updated_date_time=#{last_updated_date_time} where role_id=#{role_id}")
    void saveEditRole(Role role);
    //获取最大角色
    @Select("select max(display_order) from system_role")
    Integer selectMaxDisplayOrder();
    //新增角色
    @Insert("insert into system_role(role_id,name,description,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,display_order,audit_user_id) values(#{role_id},#{name},#{description},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{display_order},#{audit_user_id})")
    void addRole(Role role);
    //获取user-role中间表的最大主键
    @Select("select max(display_order) from system_user_role")
    Integer selectMaxDisplayOrderFromUserRole();
    //为user-role中间表添加一列
    @Insert("insert into system_user_role values(#{user_role_id},#{user_id},#{role_id},#{display_order})")
    void addUserRole(UserRole ur);
}
