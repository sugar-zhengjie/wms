package com.zj.wms.mapper;

import com.zj.wms.pojo.PurchaseItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Component
public interface PurchaseItemMapper {
    //查询所有清单
    @Select("select * from purchase_item")
    List<PurchaseItem> selectAllPurchaseItem();
    //根据清单id查询具体清单
    @Select("select * from purchase_item where purchase_item_id=#{purchase_item_id}")
    PurchaseItem selectPurchaseItemById(String purchase_item_id);
    //根据订单ID查询对应的清单
    @Select("select * from purchase_item pi,purchase p where p.purchase_no=pi.purchase_no and p.purchase_id=#{purchase_id}")
    List<PurchaseItem> selectPurchaseItemListById(String purchase_id);
    //根据订单编号查询对应清单
    @Select("select * from purchase_item where purchase_no=#{purchase_no} and is_deleted=0")
    List<PurchaseItem> selectPurchaseItemListByNo(String purchase_no);
    //查询没有收货的清单
    @Select("select * from purchase_item where purchase_no=#{purchase_no} and is_deleted=0")
    List<PurchaseItem> selectAllPurchaseItemNotReceiveByNo(String purchase_no);
    //删除清单
    @Update("update purchase_item set is_deleted=1 where purchase_item_id=#{purchase_item_id}")
    void deletePurchaseItemById(String purchase_item_id);
    //根据keyword和订单编号查询
    @Select("select * from purchase_item where purchase_no like '%${keywords}%' and is_deleted=0 and purchase_no=#{purchase_no}")
    List<PurchaseItem> selectPurchaseItemByKeywords(@Param("keywords") String keywords, @Param("purchase_no") String purchase_no);

    //获取最大排序编号
    @Select("select max(display_order) from purchase_item")
    Integer getMaxDisplayOrder();

    //新增订单项目
    @Insert("insert into purchase_item(purchase_item_id,purchase_item_no,purchase_no,matnr,matnr_description,unit,price,quantity,total_price,display_order,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time) values(#{purchase_item_id},#{purchase_item_no},#{purchase_no},#{matnr},#{matnr_description},#{unit},#{price},#{quantity},#{total_price},#{display_order},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time})")
    void addPurchaseItem(PurchaseItem purchaseItem);

    //修改数量
    @Update("update purchase_item set quantity=#{quantity} where purchase_item_id=#{purchase_item_id}")
    void editPurchaseItem(@Param("purchase_item_id") String purchase_item_id, @Param("quantity") BigDecimal quantity);

    //修改收货数量
    @Update("update purchase_item set init_received_quantity=#{newReceivedQuantity} where purchase_item_no=#{purchase_item_no}")
    void editPurchaseItemReceive(@Param("newReceivedQuantity") BigDecimal newReceivedQuantity, @Param("purchase_item_no") String purchase_item_no);

    //
    @Select("select quantity from purchase_item where purchase_item_no=#{purchase_item_no}")
    BigDecimal selectQuantity(String purchase_item_no);

    @Select("select init_received_quantity from purchase_item where purchase_item_no=#{purchase_item_no}")
    BigDecimal selectInitReceivedQuantity(String purchase_item_no);

    @Select("select * from purchase_item where purchase_item_no=#{purchase_item_no}")
    PurchaseItem getByNo(String purchase_item_no);
}