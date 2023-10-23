package com.zj.wms.mapper;

import com.zj.wms.pojo.LocationTreeItem;
import com.zj.wms.pojo.Warehouse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WarehouseMapper {

    @Select("select name,warehouse_id,warehouse_no from warehouse where werks=#{werks_id} and is_deleted=0")
    @Results({
            @Result(property = "id", column = "warehouse_id"),
            @Result(property = "no", column = "warehouse_no"),
            @Result(property = "title", column = "name"),
            @Result(property = "children", javaType = List.class, column = "warehouse_id",
                many = @Many(select = "com.zj.wms.mapper.LocationMapper.getByWarehouseIdForTree")
            )
    })
    List<LocationTreeItem> getByWerksIdForTree(String werks_id);

    @Insert("insert into warehouse(warehouse_id,warehouse_no,name,state,type,description,werks,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,display_order) values(#{warehouse_id},#{warehouse_no},#{name},#{state},#{type},#{description},#{werks},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{display_order})")
    void addWarehouse(Warehouse warehouse);

    @Select("select max(display_order) from warehouse")
    Integer selectMaxDisplayOrder();

    @Select("select warehouse_id,warehouse_no,name,state,type,description from warehouse where is_deleted=0 and warehouse_id=#{warehouse_id}")
    Warehouse selectByWarehouseId(String warehouse_id);

    @Update("update warehouse set warehouse_no=#{warehouse_no},name=#{name},state=#{state},type=#{type},description=#{description},last_updated_user_id=#{last_updated_user_id},last_updated_user_name=#{last_updated_user_name},last_updated_date_time=#{last_updated_date_time} where warehouse_id=#{warehouse_id}")
    void saveWarehouse(Warehouse warehouse);

    @Update("update warehouse set is_deleted=1 where warehouse_id=#{warehouse_id}")
    void deleteByWarehouseId(String warehouse_id);

    @Select("select * from warehouse where user_id=#{user_id}")
    List<Warehouse> selectWarehouseByUserId(String user_id);

    @Select("select * from warehouse where user_id='null'")
    List<Warehouse> selectNotWarehouseByUserId();

    @Update("update warehouse set user_id='null' where warehouse_id=#{warehouse_id}")
    void deleteUserWarehouseByWarehouseId(String warehouse_id);

    @Update("update warehouse set user_id=#{user_id} where warehouse_id=#{warehouse_id}")
    void addUserWarehouseByWarehouseId(@Param("user_id") String user_id, @Param("warehouse_id") String warehouse_id);


}
