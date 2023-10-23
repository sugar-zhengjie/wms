package com.zj.wms.mapper;

import com.zj.wms.pojo.PurchaseReceive;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PurchaseReceiveMapper {
    //根据purchase_no查询对应的收货单
    @Select("select * from purchase_receive where purchase_no=#{purchase_no}")
    PurchaseReceive selectPurchaseReceiveByPurchaseNo(String purchase_no);
    //排序
    @Select("select max(display_order) from purchase_receive")
    Integer selectMaxDisplayOrder();
    //创建收货单
    @Insert("insert into purchase_receive(purchase_receive_id,purchase_receive_no,purchase_no,opt_user_id,state,display_order,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,audit_user_id) values(#{purchase_receive_id},#{purchase_receive_no},#{purchase_no},#{opt_user_id},#{state},#{display_order},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{audit_user_id})")
    void addPurchaseReceive(PurchaseReceive purchaseReceive);
    //查询所有到货单
    @Select("select * from purchase_receive where state=0;")
    List<PurchaseReceive> selectAllPurchaseReceive();

    @Update("update purchase_receive set state=1 where purchase_receive_no=#{purchase_receive_no}")
    void closePurchaseReceive(String purchase_receive_no);

    @Update("update purchase_receive set state=0 where purchase_receive_no=#{purchase_receive_no}")
    void commit(String purchase_receive_no);
}