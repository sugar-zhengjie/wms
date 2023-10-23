package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
//收货单项目
public class PurchaseReceiveItem {
    private String purchase_receive_item_id;    //收货单项目id
    private String purchase_receive_item_no;    //收货项目编号
    private String purchase_receive_no;     //收货单编号
    private String purchase_no;     //订单编号
    private String purchase_item_no;    //订单行项目编号
    private String werks;       //工厂
    private String lgort;   //库存地点
    private String matnr;   //物料编号
    private String lot;     //批次号
    private BigDecimal quantity;    //数量
    private String location_no;     //库位编号
    private String delivery_no;     //到货单编号
    private String remark;  //备注
    private Integer state;  //1-冻结 0-非冻结
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
