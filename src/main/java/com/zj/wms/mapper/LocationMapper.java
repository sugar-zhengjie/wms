package com.zj.wms.mapper;

import com.zj.wms.pojo.Location;
import com.zj.wms.pojo.LocationTreeItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LocationMapper {

    @Select("select name,location_id,Location_no from location where lgort=#{warehouse_id} and is_deleted=0")
    @Results({
            @Result(property = "id", column = "location_id"),
            @Result(property = "no", column = "location_no"),
            @Result(property = "title", column = "name"),
    })
    List<LocationTreeItem> getByWarehouseIdForTree(String warehouse_id);

    @Select("select max(display_order) from location")
    Integer selectMaxDisplayOrder();

    @Insert("insert into location(location_id,location_no,name,length,width,height,weight_limit,lgort,state,description,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,display_order) values(#{location_id},#{location_no},#{name},#{length},#{width},#{height},#{weight_limit},#{lgort},#{state},#{description},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{display_order})")
    void addLocation(Location location);

    @Select("select location_id,location_no,name,length,width,height,weight_limit,state,description from location where is_deleted=0 and location_id=#{location_id}")
    Location selectByLocationId(String location_id);

    @Update("update location set location_no=#{location_no},name=#{name},length=#{length},width=#{width},height=#{height},weight_limit=#{weight_limit},state=#{state},description=#{description},last_updated_user_id=#{last_updated_user_id},last_updated_user_name=#{last_updated_user_name},last_updated_date_time=#{last_updated_date_time} where location_id=#{location_id}")
    void saveLocation(Location location);

    @Update("update location set is_deleted=1 where location_id=#{location_id}")
    void deleteByLocationId(String location_id);

}
