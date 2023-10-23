package com.zj.wms.mapper;

import com.zj.wms.pojo.Material;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MaterialMapper {

    @Select("select material_id,matnr as material_no,name,material_type_name,specifications,vendor,unit,remark from material where is_deleted=0 and material_type_id=#{material_type_id} and matnr like '%${keywords_no}%' and name like '%${keywords_name}%' and name like '%${keywords_specifications}%'")
    List<Material> getMaterialsByKeywordsAndMaterialId(@Param("material_type_id") String material_type_id, @Param("keywords_no") String keywords_no, @Param("keywords_name") String keywords_name, @Param("keywords_specifications") String keywords_specifications);

    @Select("select material_id,matnr as material_no,name,material_type_name,specifications,vendor,unit,remark from material where is_deleted=0 and matnr like '%${keywords_no}%' and name like '%${keywords_name}%' and name like '%${keywords_specifications}%'")
    List<Material> getMaterialsByKeywords(@Param("keywords_no") String keywords_no, @Param("keywords_name") String keywords_name, @Param("keywords_specifications") String keywords_specifications);

    @Select("select max(display_order) from material")
    Integer selectMaxDisplayOrder();

    @Insert("insert into material(material_id,material_type_id,material_type_name,matnr,name,specifications,vendor,unit,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,display_order) values(#{material_id},#{material_type_id},#{material_type_name},#{material_no},#{name},#{specifications},#{vendor},#{unit},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{display_order})")
    void addMaterial(Material material);

    @Select("select material_id,matnr as material_no,material_type_id,name,specifications,vendor from material where is_deleted=0 and material_id=#{material_id}")
    Material selectByMaterialId(String material_id);

    @Update("update material set material_type_id=#{material_type_id},material_type_name=#{material_type_name},matnr=#{material_no},name=#{name},specifications=#{specifications},vendor=#{vendor},unit=#{unit},last_updated_user_id=#{last_updated_user_id},last_updated_user_name=#{last_updated_user_name},last_updated_date_time=#{last_updated_date_time} where material_id=#{material_id}")
    void saveMaterial(Material material);

    @Update("update material set is_deleted=1 where material_id=#{material_id}")
    void deleteByMaterialId(String material_id);
}
