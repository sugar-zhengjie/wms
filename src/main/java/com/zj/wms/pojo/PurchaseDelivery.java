package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
//送货单
public class PurchaseDelivery {
    private String purchase_delivery_id;    //送货单id
    private String supplier_no;    //供应商编号
    private String supplier_name;   //供应商名称
    private String werks;   //工厂
    private String werks_name;  //工厂名称
    private String company_name;    //公司名称
    private String company_code;    //公司编号
    private String delivery_no;     //发货单编号
    private String delivery_type;    //发货类型
    private String delivery_site;   //收货地址
    private String lgort_code;      //库存地点代码
    private String lgort_name;      //库存地点名称
    private String forecast_arrival_time;   //预计到货时间
    private String delivery_status; //发货状态
    private String delivery_time;   //发货时间
    private String delivery_user_name;  //发货人姓名
    private String remarks;     //发货备注
    private String cancel_status;   //取消发货状态
    private String cancel_time; //取消发货时间
    private String cancel_user_name;    //取消发货人姓名
    private String return_flag;     //退回标识
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
