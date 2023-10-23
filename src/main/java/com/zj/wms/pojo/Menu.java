package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Menu {
    private String menu_id;//菜单编号
    private String menu_no;//菜单No
    private String parent_menu_id;//隶属菜单
    private String name;//菜单名称
    private String link;//菜单链接地址
    private String module;//模块
    private String style;//菜单样式
    private String icon;//菜单图标
    private int is_show;//是否展示 0-展示  1-不展示
    private int type;//菜单类型
    private String target;//打开目标
    private int haschild;//是否有子节点
    private int layer;//层级
    private String path;//路径
    private int display_order;//排序
    private int state;//状态
    private String create_user_id;//创建人id
    private String create_user_name;//创建人姓名
    private Date created_user_time;//创建日期时间
    private String last_updated_user_id;//最后更新人id
    private String last_updated_user_name;//最后更新人姓名
    private Date last_updated_user_time;//最后更新时间
    private int audit_state;//审核状态
    private String audit_user_id;//审核人id
}
