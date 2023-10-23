package com.zj.wms.mapper;

import com.zj.wms.pojo.Shelves;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ShelvesMapper {

    @Select("select max(display_order) from shelves")
    Integer selectMaxDisplayOrder();

    @Insert("insert into shelves(shelves_id,shelves_no,type,purchase_no,purchase_apply_no,display_order,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time) values(#{shelves_id},#{shelves_no},#{type},#{purchase_no},#{purchase_apply_no},#{display_order},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time})")
    void addShelves(Shelves shelves);
}
