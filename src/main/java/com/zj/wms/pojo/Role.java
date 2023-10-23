package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Role {
    private String role_id;//角色编号
    private String name;//角色名称
    private String description;//角色描述
    private int state;//状态
    private String created_user_id;//创建人id
    private String created_user_name;//创建人姓名
    private Date created_date_time;//创建日期时间
    private String last_updated_user_id;//最后更新人id
    private String last_updated_user_name;//最后更新人姓名
    private Date last_updated_date_time;//最后更新时间
    private int audit_state;//审核状态
    private String audit_user_id;//审核人id
    private String audit_user_name;//审核人姓名
    private Date audit_user_time;//审核日期时间
    private int is_delete;//是否删除
    private int display_order;//排序
}
