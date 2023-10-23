package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class User {

    private String user_id;     //用户id
    private String tenant_id;   //租户id
    private String account;     //账号
    private String password;    //密码
    private String user_type;   //用户类型
    private Integer administrator_level;    //管理员等级 0-非管理员 1-管理员 0-超级管理员
    private String user_no;     //用户编号
    private String name;        //姓名
    private String nick_name;   //昵称
    private String organization_units_id;  //机构单元id
    private String organization_units_no;   //机构单元编号
    private String organization_units_name; //机构单元名称
    private String department_id;   //部门id
    private String department_no;   //部门编号
    private String department_name; //部门名称
    private String station_id;      //岗位id
    private String station_no;      //岗位编号
    private String station_name;    //岗位名称
    private String job_id;          //职位id
    private String job_no;        //职位编号
    private String job_name;        //职位名称
    private String mobile;          //手机
    private String telephone;       //座机
    private String email;           //邮箱
    private Integer sex;        //性别 1-男 2-女
    private String avatar;   //头像
    private Integer id_type;    //证件类型
    private String id_number;   //证件号码
    private String id_attachment;   //证件附件
    private String salt;    //盐值
    private String theme;   //系统风格
    private String language;    //界面语言
    private Date join_date;     //加入时间
    private String reg_ip;      //注册ip
    private Integer login_count;    //登录次数
    private Date last_login_time;   //最后一次登录时间
    private String last_login_ip;   //最后一次登录ip
    private Integer is_allow_delete;    //系统内置管理员不可被删除 0-可以被删除 1-不可被删除
    private Integer display_order;      //排序
    private Integer state;      //状态
    private String created_user_id;     //创建人id
    private String created_user_name;   //创建人姓名
    private Date created_date_time;     //创建日期时间
    private String last_updated_user_id;    //最后更新人id
    private String last_updated_user_name;  //最后更新人姓名
    private Date last_updated_date_time;    //最后更新时间
    private Integer audit_state;    //审核状态
    private String audit_user_id;   //审核人id
    private String audit_user_name; //审核人姓名
    private Date audit_date_time;   //审核日期时间
    private Integer is_deleted;     //是否已删除
    private String remark;      //备注
}
