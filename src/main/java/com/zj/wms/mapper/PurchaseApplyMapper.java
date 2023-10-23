package com.zj.wms.mapper;

import com.zj.wms.pojo.PurchaseApply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PurchaseApplyMapper {

    //获得最大排序
    @Select("select max(display_order) from purchase_apply")
    Integer getMaxDisplayOrder();

    //添加入库申请单
    @Insert("insert into purchase_apply(purchase_apply_id,purchase_apply_no,purchase_receive_no,purchase_no,display_order,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time) values(#{purchase_apply_id},#{purchase_apply_no},#{purchase_receive_no},#{purchase_no},#{display_order},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time})")
    void addPurchaseApply(PurchaseApply purchaseApply);
}
