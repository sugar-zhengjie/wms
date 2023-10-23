package com.zj.wms.mapper;

import com.zj.wms.pojo.Menu;
import com.zj.wms.pojo.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface MenuMapper {
    //根据账号名查询所拥有的权限
    @Select("select m.* from system_users u,system_user_role ur,system_role_menu rm,system_menu m where u.user_id=ur.user_id and ur.role_id=rm.role_id and rm.menu_id=m.menu_id and account=#{account};")
    List<Menu> getPermissionByAccount(String account);
    //查询所有权限
    @Select("select * from system_menu")
    List<Menu> getAllMenu();
    //根据父类id查询一个大类的权限
    @Select("select * from system_menu where parent_menu_id=#{parent_menu_id}")
    List<Menu> getMenuByParentMenuId(String parent_menu_id);
    //根据角色id删除所有对应的权限
    @Delete("delete from system_role_menu where role_id=#{role_id}")
    void deleteRoleMenu(String role_id);
    //根据权限名查询权限id
    @Select("select menu_id from system_menu where name=#{name}")
    String selectMenuIdByName(String name);
    //查询role-menu中间表的最大排序数
    @Select("select max(display_order) from system_role_menu")
    Integer selectMaxDisplayOrder();
    //中间表role-menu存储
    @Insert("insert into system_role_menu(role_menu_id,menu_id,role_id,display_order) values(#{role_menu_id},#{menu_id},#{role_id},#{display_order})")
    void saveRoleMenu(RoleMenu roleMenu);
}
