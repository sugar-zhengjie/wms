package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Supplier {
    private String supplier_id;     //供应商id
    private String supplier_no;     //供应商编号
    private String name;        //名称
    private String ename;       //英文名称
    private String werks;       //工厂
    private String description;     //描述
    private String regions_group_id;    //区域组id
    private String retrieve1;   //检索项1
    private String retrieve2;   //检索项2
    private String contact_name;    //联系人姓名
    private String contact_phone;   //联系人手机
    private String contact_email;   //联系人邮箱
    private String contact_name2;   //联系人姓名2
    private String contact_phone2;  //联系人电话2
    private String contact_email2;  //联系人邮箱2
    private String region_id;   //区域id
    private String country;     //国家
    private String city;        //城市
    private String region;      //地区
    private String street_address;  //街道地址
    private String post_code;   //邮编
    private String region_id2;  //区域id2
    private String street_address2; //街道地址2
    private String post_code2;  //邮编2
    private String official_website;    //官网
    private String duty_paragraph;  //税号
    private String bank;    //开户银行
    private String bank_account;    //开户账号
    private String remark;  //备注
    private String short_name;
    private String spell;
    private String currency;
    private String sales_range;
    private String delete_mark;     //删除标识
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
    private Integer is_deleted;
}
