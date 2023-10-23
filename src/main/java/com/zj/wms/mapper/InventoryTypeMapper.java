package com.zj.wms.mapper;

import com.zj.wms.pojo.InventoryType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InventoryTypeMapper {

    @Select("select inventory_type_id,io_type,inventory_type_no,name,remark from inventory_type where is_deleted=0 and name like '%${keywords}%'")
    List<InventoryType> getInventoryTypesByKeywords(@Param("keywords") String keywords);

    @Select("select max(display_order) from inventory_type")
    Integer selectMaxDisplayOrder();

    @Insert("insert into inventory_type(inventory_type_id,inventory_type_no,io_type,name,remark,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,display_order) values(#{inventory_type_id},#{inventory_type_no},#{io_type},#{name},#{remark},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{display_order})")
    void addInventoryType(InventoryType inventoryType);

    @Select("select inventory_type_id,inventory_type_no,io_type,name,remark from inventory_type where is_deleted=0 and inventory_type_id=#{inventory_type_id}")
    InventoryType selectByInventoryTypeId(String inventory_type_id);

    @Update("update inventory_type set inventory_type_no=#{inventory_type_no},io_type=#{io_type},name=#{name},remark=#{remark},last_updated_user_id=#{last_updated_user_id},last_updated_user_name=#{last_updated_user_name},last_updated_date_time=#{last_updated_date_time} where inventory_type_id=#{inventory_type_id}")
    void saveInventoryType(InventoryType inventoryType);

    @Update("update inventory_type set is_deleted=1 where inventory_type_id=#{inventory_type_id}")
    void deleteByInventoryTypeId(String inventory_type_id);
    
}
