package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class PurchaseItem {
    private String purchase_item_id;    //采购项目id
    private String purchase_item_no;     //采购项目编号
    private String purchase_no;     //采购订单编号
    private String werks;       //工厂
    private String lgort;   //库存地点
    private String matnr;   //物料编号
    private String apply_user_id;   //申请人id
    private String apply_user_name;  //申请人姓名
    private String apply_user_email;    //申请人邮箱
    private BigDecimal quantity;    //数量
    private BigDecimal price;       //单价
    private BigDecimal total_price;     //总价
    private String unit;    //单位
    private String out_source_matnr;        //委外组件物料
    private BigDecimal out_source_quantity;     //委外数量
    private String out_source_unit;     //委外单位
    private String sales_order_no;      //销售订单号
    private String sales_order_item_no; //销售订单行项目
    private String is_closed;   //是否关闭
    private String remark;      //备注
    private String matnr_group; //物料组
    private String is_return;   //是否为退货项目
    private String is_limit;    //是否限制数量
    private BigDecimal min_limit;   //限制最低数量
    private BigDecimal max_limit;   //限制最大数量
    private String put_type;    //采购方式
    private BigDecimal unit_mol;    //基本单位分子
    private BigDecimal unit_den;    //基本单位分母
    private String matnr_description;   //物料描述
    private Integer quality_state;  //质检状态
    private BigDecimal init_received_quantity;  //已收货数量
    private Integer state;  //入库状态
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
