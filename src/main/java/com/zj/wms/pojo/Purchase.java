package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Purchase {
    private String purchase_id;     //采购订单id
    private String purchase_no;     //采购订单编号
    private String werks;       //工厂
    private String supplier_id;     //供应商id
    private String apply_organization_id;   //申请部门
    private String apply_user_id;   //申请人
    private String order_type;      //订单类型
    private Date order_date_time;   //订单日期时间
    private Date plan_arrival_date_time;    //计划到货时间
    private Integer is_closed;      //0-未关闭 1-已关闭
    private String remark;      //备注
    private String approval_status;     //审批状态
    private String put_type;    //采购方式
    private Integer receive_state;  //收货状态
    private Integer quality_state;  //质检状态
    private Integer state;      //入库状态
    private Integer display_order;      //排序
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
