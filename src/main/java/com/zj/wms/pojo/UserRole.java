package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UserRole {
    private String user_role_id;//编号
    private String role_id;//角色编号
    private String user_id;//用户编号
    private Integer display_order;//排序
}
