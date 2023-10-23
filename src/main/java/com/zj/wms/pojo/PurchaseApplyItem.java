package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class PurchaseApplyItem {
    private String purchase_apply_item_id;  //生产入库项id
    private String purchase_apply_item_no;  //生产入库项编号
    private String purchase_apply_no;   //生产入库编号
    private String werks_;  //工厂
    private String lgort_;  //库存地点
    private String location_no; //库位
    private String matnr;   //物料号
    private String lot;     //批次
    private BigDecimal quantity;    //数量
    private String unit;    //计量单位
    private String purchase_no; //订单号
    private Integer data_source;    //0-系统自建 1-普MES传递 3-特棒MES传递
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
