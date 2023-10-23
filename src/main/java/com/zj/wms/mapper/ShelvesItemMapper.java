package com.zj.wms.mapper;

import com.zj.wms.pojo.ShelvesItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ShelvesItemMapper {

    @Select("select max(display_order) from shelves_item")
    Integer getMaxDisplayOrder();

    @Insert("insert into shelves_item(shelves_item_id,shelves_item_no,shelves_no,location_no,matnr,quantity,display_order,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time) values(#{shelves_item_id},#{shelves_item_no},#{shelves_no},#{location_no},#{matnr},#{quantity},#{display_order},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time})")
    void addShelvesItem(ShelvesItem shelves_item);
}
