package com.zj.wms.mapper;

import com.zj.wms.pojo.MaterialType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MaterialTypeMapper {

    @Select("select material_type_id,material_type_no,name,remark from material_type where is_deleted=0 and name like '%${keywords}%'")
    List<MaterialType> getMaterialTypesByKeywords(@Param("keywords") String keywords);

    @Select("select max(display_order) from material_type")
    Integer selectMaxDisplayOrder();

    @Insert("insert into material_type(material_type_id,material_type_no,name,remark,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,display_order) values(#{material_type_id},#{material_type_no},#{name},#{remark},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{display_order})")
    void addMaterialType(MaterialType materialType);

    @Select("select material_type_id,material_type_no,name,remark from material_type where is_deleted=0 and material_type_id=#{material_type_id}")
    MaterialType selectByMaterialTypeId(String material_type_id);

    @Update("update material_type set material_type_no=#{material_type_no},name=#{name},remark=#{remark},last_updated_user_id=#{last_updated_user_id},last_updated_user_name=#{last_updated_user_name},last_updated_date_time=#{last_updated_date_time} where material_type_id=#{material_type_id}")
    void saveMaterialType(MaterialType materialType);

    @Update("update material_type set is_deleted=1 where material_type_id=#{material_type_id}")
    void deleteByMaterialTypeId(String material_type_id);

}
