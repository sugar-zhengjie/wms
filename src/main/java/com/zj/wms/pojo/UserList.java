package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
//该表用于用户管理模块
public class UserList {
    private String user_id; //用户id
    private String account; //账号
    private String name;  //姓名
    private Integer display_order;//排序
    private Integer state; //状态
    private String remark; //备注
    private String role_id;//角色编号
    private String roleName;//角色名称
}
