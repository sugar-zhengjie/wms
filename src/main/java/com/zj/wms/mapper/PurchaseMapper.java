package com.zj.wms.mapper;

import com.zj.wms.pojo.Purchase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PurchaseMapper {
    //查询所有订单
    @Select("select * from purchase where is_closed=0")
    List<Purchase> selectAllPurchase();
    //根据订单id查找订单
    @Select("select * from purchase where purchase_id=#{purchase_id}")
    Purchase selectPurchaseByPurchaseId(String purchase_id);
    //根据订单号查询
    @Select("select * from purchase where purchase_no like '%${keywords}%' and is_closed=0")
    List<Purchase> selectPurchaseByKeywords(String keywords);

    @Select("select max(display_order) from purchase")
    Integer selectMaxDisplayOrder();
    //新增订单
    @Insert("insert into purchase(purchase_id,purchase_no,apply_organization_id,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,display_order) values(#{purchase_id},#{purchase_no},#{apply_organization_id},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{display_order})")
    void addPurchase(Purchase purchase);

    //订单收货完成
    @Update("update purchase set receive_state=1 where purchase_no=#{purchase_no}")
    void editReceiveStateByPurchaseNo(String purchase_no);

}