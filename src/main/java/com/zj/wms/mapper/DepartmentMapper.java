package com.zj.wms.mapper;

import com.zj.wms.pojo.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {

    @Select("select department_id,department_no,name,remark from system_department where is_deleted=0 and department_no like '%${keywords_no}%' and name like '%${keywords_name}%'")
    List<Department> getDepartmentsByKeywords(@Param("keywords_no") String keywords_no, @Param("keywords_name") String keywords_name);

    @Select("select max(display_order) from system_department")
    Integer selectMaxDisplayOrder();

    @Insert("insert into system_department(department_id,department_no,name,remark,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,display_order) values(#{department_id},#{department_no},#{name},#{remark},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{display_order})")
    void addDepartment(Department department);

    @Select("select department_id,department_no,name,remark from system_department where is_deleted=0 and department_id=#{department_id}")
    Department selectByDepartmentId(String department_id);

    @Update("update system_department set department_no=#{department_no},name=#{name},remark=#{remark},last_updated_user_id=#{last_updated_user_id},last_updated_user_name=#{last_updated_user_name},last_updated_date_time=#{last_updated_date_time} where department_id=#{department_id}")
    void saveDepartment(Department department);

    @Update("update system_department set is_deleted=1 where department_id=#{department_id}")
    void deleteByDepartmentId(String department_id);
}
