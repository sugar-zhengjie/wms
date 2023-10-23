package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Werks {
    private String werks_id;    //工厂id
    private String werks_no;    //工厂编号
    private String parent_werks_id;  //隶属工厂
    private String organization_no; //隶属组织机构
    private String name;    //名称
    private String short_name;  //简称
    private String description; //描述
    private String address;     //地址
    private Integer layer;      //层级
    private Integer has_child;  //0-不存在 1-存在
    private String path;    //路径
    private Integer state;      //状态
    private Integer display_order;  //排序
    private String created_user_id; //创建人id
    private String created_user_name;   //创建人姓名
    private Date created_date_time;     //创建时间
    private String last_updated_user_id;    //最后更新人
    private String last_updated_user_name;  //最后更新人姓名
    private Date last_updated_date_time;    //最后更新时间
    private Integer audit_state;    //审核状态
    private String audit_user_id;   //审核人姓名
    private Date audit_date_time;   //审核时间
    private Integer is_deleted;     //是否已删除
    private String remark;      //备注
}
