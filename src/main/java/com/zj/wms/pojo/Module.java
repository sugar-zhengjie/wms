package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@NoArgsConstructor
@ToString
public class Module {
    private String module_id;//菜单ID
    private String module_no;//菜单No
    private String name;//菜单名称
    private String icon;//菜单图标
    private int is_show;//是否展示 0-展示  1-不展示
    private int display_order;//排序
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
    private Date audit_date_time;//审核日期时间
    private int is_deleted;//是否删除
}
