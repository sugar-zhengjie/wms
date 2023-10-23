package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Location {
    private String location_id;     //库位id
    private String parent_location_id;      //隶属库位
    private String location_no;     //库位编号
    private String parent_location_no;      //隶属库位编号
    private String werks;       //工厂
    private String name;        //名称
    private String lgort;       //隶属仓库
    private String location_area_no;        //隶属库区
    private String location_group_no;       //隶属组
    private String description;         //描述
    private Integer type;       //类型
    private BigDecimal volume_restriction;      //体积限制
    private BigDecimal weight_limit;    //重量限制
    private BigDecimal container_number_limit;  //箱数限制
    private BigDecimal tray_limit;      //托盘限制
    private BigDecimal length;      //长度
    private BigDecimal width;   //宽度
    private BigDecimal height;  //高度
    private BigDecimal coordinate_x;    //坐标x
    private BigDecimal coordinate_y;    //坐标y
    private BigDecimal coordinate_z;    //坐标z
    private BigDecimal pixel_x;     //像素x
    private BigDecimal piel_y;      //像素y
    private BigDecimal space_layer;     //空间层数
    private String allow_mixed_product;  //允许Y  不允许X  是否允许同一库位混合放置多个产品
    private String allow_mixed_product_lot; //允许Y  不允许X  是否允许同一库位混合放置同一产品的不同批次。
    private Integer layer;      //层级
    private Integer has_child;  //0-不存在   1存在
    private String path;    //路径
    private Integer state;  //状态
    private Integer display_order;  //排序
    private String created_user_id;     //创建人id
    private String created_user_name;   //创建人姓名
    private Date created_date_time;     //创建日期时间
    private String last_updated_user_id;    //最后更新人id
    private String Last_updated_user_name;   //最后更新人姓名今天
    private Date last_updated_date_time;        //最后更新时间
    private Integer audit_state;        //审核状态
    private String audit_user_id;       //审核人id
    private String audit_user_name;     //审核人姓名
    private Date audit_date_time;       //审核日期时间
    private Integer is_deleted;         //是否已删除
}