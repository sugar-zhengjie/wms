package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class RoleMenu {
    private String role_menu_id;//编号
    private String role_id;//角色编号
    private String menu_id;//权限编号
    private String remarks;//备注
    private int display_order;//排序
}
