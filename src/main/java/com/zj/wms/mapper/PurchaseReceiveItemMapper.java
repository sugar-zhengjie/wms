package com.zj.wms.mapper;

import com.zj.wms.pojo.PurchaseReceiveItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PurchaseReceiveItemMapper {
    @Select("select max(display_order) from purchase_receive_item")
    Integer selectMaxDisplayOrder();
    //创建收货清单
    @Insert("insert into purchase_receive_item(purchase_receive_item_id,purchase_receive_item_no,purchase_receive_no,purchase_no,purchase_item_no,matnr,quantity,display_order,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,audit_user_id) values(#{purchase_receive_item_id},#{purchase_receive_item_no},#{purchase_receive_no},#{purchase_no},#{purchase_item_no},#{matnr},#{quantity},#{display_order},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{audit_user_id})")
    void addPurchaseReceiveItem(PurchaseReceiveItem purchaseReceiveItem);

    @Select("select * from purchase_receive_item")
    List<PurchaseReceiveItem> getAllItem(String purchase_receive_no);
}