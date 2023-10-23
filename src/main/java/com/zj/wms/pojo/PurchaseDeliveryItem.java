package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
//送货单项
public class PurchaseDeliveryItem {
    private String purchase_delivery_item_id;   //送货单项id
    private String matnr;   //物料编号
    private String item_no; //订单行号
    private String order_no;   //订单号
    private String delivery_no; //发货单号
    private String delivery_item_no;    //发货单行号
    private String material_name;   //物料名称
    private String is_free; //免费标识
    private String exemption_flag;  //免检标识
    private BigDecimal delivery_qty;    //发货数量
    private String remarks;     //备注
    private String cancel_status;   //取消发货状态
    private String cancel_time;     //取消发货时间
    private String cancel_use_name; //取消发货人姓名
    private String create_time;     //创建时间
    private String last_modify_time;    //最后修改时间
    private Integer state;  //状态
    private Integer display_order;  //排序
    private String created_user_id;     //创建人id
    private String created_user_name;   //创建人姓名
    private Date created_date_time;     //创建日期时间
    private String last_updated_user_id;    //最后更新人id
    private String Last_updated_user_name;   //最后更新人姓名
    private Date last_updated_date_time;        //最后更新时间
    private Integer audit_state;        //审核状态
    private String audit_user_id;       //审核人id
    private String audit_user_name;     //审核人姓名
    private Date audit_date_time;       //审核日期时间
    private Integer is_deleted;         //是否已删除
}
