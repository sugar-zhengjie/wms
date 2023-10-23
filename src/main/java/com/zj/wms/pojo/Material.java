package com.zj.wms.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Material {
    private String material_id;     //物料id
    private String material_no;       //物料编号
    private String werks;       //工厂
    private String name;        //名称
    private String enname;      //英文名称
    private String material_group_id;
    private String material_group_no;
    private String specifications;  //规格型号
    private String specification_quantity;
    private String barcode;     //条码
    private String unit;        //计量单位
    private Integer type;        //0-非N级物料  3-N级物料
    private String vendor;      //生产厂商
    private BigDecimal max_inventory;       //库存上限
    private BigDecimal min_inventory;       //库存下限
    private BigDecimal sales_price;         //销售价格
    private BigDecimal sales_discount;      //销售折扣
    private String init_storage;        //初始库存
    private Integer purchase_type;      //1-自制  3-外购  13-自制+外购
    private String receive_storage_id;     //收货仓库id
    private String deliver_storage_id;     //发货仓库id
    private Integer is_open_batch;      //1-启用批次     2-不启用批次
    private String remark;          //备注
    private BigDecimal spq;         //SPQ
    private String receive_storage_no;      //收货仓库编号
    private String deliver_storage_no;      //发货仓库编号
    private Integer is_matnr_lot2_mes;      //0-不传输  1-传输
    private String material_class_id;
    private String material_class_ename;
    private String material_type_id;
    private String material_type_name;
    private String description;         //描述
    private String brand_description;
    private Integer state;      //状态
    private Integer display_order;      //排序
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
    private Integer is_deleted;     //是否已删除
    private String delete_mark;     //删除标识
}
