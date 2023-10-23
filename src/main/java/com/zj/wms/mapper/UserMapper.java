package com.zj.wms.mapper;

import com.zj.wms.pojo.Role;
import com.zj.wms.pojo.User;
import com.zj.wms.pojo.UserList;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {


    @Select("select * from system_users where account=#{account} and password=#{password} and state=0 and is_deleted=0")
    //根据传入的User查询数据库匹配账户密码
    User login(User u);
    @Select("select * from system_users where account=#{account}")
    User selectUser(String account);
    @Select("select * from system_users where user_id=#{user_id}")
    User findUserByUserId(String user_id);
    @Select("select * from system_users")
    List<User> getAllUsers();
    @Select("select u.user_id as user_id,u.account as account,u.name as name,u.state as state,u.remark as remark,u.display_order as display_order,r.role_id,r.name as roleName from system_users u,system_role r,system_user_role ur where u.user_id=ur.user_id and ur.role_id=r.role_id and r.role_id like '%${roleCheck}%' and u.state like '%${stateCheck}%' and u.name like '%${name}%' and u.account like '%${account}%'")
    List<UserList> getUsersByCondition(@Param("stateCheck") String stateCheck, @Param("roleCheck") String roleCheck, @Param("account") String account, @Param("name") String name);

    @Update("update system_users set password=#{password},last_updated_user_id=#{last_updated_user_id},last_updated_user_name=#{last_updated_user_name},last_updated_date_time=#{last_updated_date_time} where user_id=#{user_id}")
    void saveUser(User user);
    @Update("update system_users set account=#{account},name=#{name},remark=#{remark},last_updated_user_id=#{last_updated_user_id},last_updated_user_name=#{last_updated_user_name},last_updated_date_time=#{last_updated_date_time} where user_id=#{user_id}")
    void saveEditUser(User user);

    //根据user_id查找对应的角色
    @Select("select r.* from system_users u,system_user_role ur,system_role r where u.user_id=ur.user_id and ur.role_id=r.role_id and u.user_id=#{user_id}")
    Role findRoleByUserId(String user_id);
    //删除用户对应的角色
    @Delete("update system_user_role set role_id=#{role_id} where user_id=#{user_id}")
    void updateUserRole(@Param("user_id")String user_id,@Param("role_id") String role_id);

    @Select("select max(display_order) from system_users")
    Integer selectMaxDisplayOrder();

    @Insert("insert into system_users(user_id,tenant_id,account,password,name,organization_units_id,department_id,station_id,job_id,sex,id_type,display_order,state,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,audit_state,audit_user_id,is_deleted,remark) values(#{user_id},#{tenant_id},#{account},#{password},#{name},#{organization_units_id},#{department_id},#{station_id},#{job_id},#{sex},#{id_type},#{display_order},#{state},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{audit_state},#{audit_user_id},#{is_deleted},#{remark})")
    void addUser(User user);

    //根据userid修改状态
    @Update("update system_users set state=1 where user_id=#{user_id}")
    void stopAccount(String user_id);
    @Update("update system_users set state=0 where user_id=#{user_id}")
    void startAccount(String user_id);
}
