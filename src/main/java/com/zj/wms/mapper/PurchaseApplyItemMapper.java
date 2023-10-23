package com.zj.wms.mapper;

import com.zj.wms.pojo.PurchaseApplyItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PurchaseApplyItemMapper {

    //获得最大排序
    @Select("select max(display_order) from purchase_apply_item")
    Integer getMaxDisplayOrder();

    //新增申请入库项
    @Insert("insert into purchase_apply_item(purchase_apply_item_id,purchase_apply_item_no,purchase_apply_no,purchase_no,matnr,quantity,display_order,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time) values(#{purchase_apply_item_id},#{purchase_apply_item_no},#{purchase_apply_no},#{purchase_no},#{matnr},#{quantity},#{display_order},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time})")
    void addItem(PurchaseApplyItem apply_item);

    //修改库位
    @Update("update purchase_apply_item set location_no=#{location_no} where purchase_apply_item_id=#{purchase_apply_item_id}")
    void editLocation(@Param("purchase_apply_item_id") String purchase_apply_item_id, @Param("location_no") String location_no);

    @Select("select * from purchase_apply_item where purchase_apply_no = #{purchase_apply_no}")
    List<PurchaseApplyItem> getAllItem(String purchase_apply_no);

    @Select("select * from purchase_apply_item where purchase_apply_no=#{purchase_apply_no}")
    List<PurchaseApplyItem> getByApplyNo(String purchase_apply_no);
}
