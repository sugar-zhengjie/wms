package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class InventoryType {
    private String inventory_type_id;   //出入库类型id;
    private String inventory_type_no;   //出入库类型编号
    private String name;        //名称
    private String io_type;     //出 入
    private String description;     //描述
    private Integer state;      //状态
    private Integer display_order;  //排序
    private String created_user_id;     //创建人id
    private String created_user_name;   //创建人姓名
    private Date created_date_time;     //创建时间
    private String last_updated_user_id;    //最后更新人id
    private String last_updated_user_name;  //最后更新人姓名
    private Date last_updated_date_time;    //最后更新时间
    private Integer audit_state;    //审核状态
    private String audit_user_id;   //审核人id
    private String audit_user_name; //审核人姓名
    private Date audit_date_time;   //审核时间
    private Integer is_deleted;     //是否已删除
    private String remark;      //备注

}
