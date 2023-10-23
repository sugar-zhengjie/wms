package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Unit {
    private String multi_unit_id;   //多单位id
    private String multi_unit_no;   //多单位编号
    private String werks;       //工厂
    private String matnr;       //物料
    private String unit;        //基本单位
    private String supplementary_unit;  //辅助单位
    private BigDecimal unit_ratio;         //基本单位比值
    private BigDecimal supplementary_unit_ratio;    //辅助单位比值
    private Integer is_default_supplementary_unit;      //1-默认辅助单位 0-非默认
    private String description;     //描述
    private Integer state;      //状态
    private Integer display_order;      //排序
    private String created_user_id;     //创建人id
    private String created_user_name;   //创建人姓名
    private Date created_date_time;     //创建时间
    private String last_updated_user_id;    //最后更新人id
    private String last_updated_user_name;  //最后更新人姓名
    private Date last_updated_date_time;    //最后更新时间
    private Integer is_deleted;         //是否已删除
    private String remark;      //备注
}
