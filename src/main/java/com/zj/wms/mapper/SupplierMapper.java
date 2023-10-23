package com.zj.wms.mapper;

import com.zj.wms.pojo.Supplier;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SupplierMapper {

    @Select("select supplier_id,supplier_no,name,contact_name,contact_phone,contact_email,street_address,region,post_code,remark from supplier where is_deleted=0 and name like '%${keywords}%'")
    List<Supplier> getSuppliersByKeywords(String keywords);

    @Select("select max(display_order) from supplier")
    Integer getMaxDisplayOrder();

    @Insert("insert into supplier(supplier_id,supplier_no,name,contact_name,contact_phone,contact_email,street_address,region,post_code,remark,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,display_order) values(#{supplier_id},#{supplier_no},#{name},#{contact_name},#{contact_phone},#{contact_email},#{street_address},#{region},#{post_code},#{remark},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{display_order})")
    void addSupplier(Supplier supplier);

    @Select("select supplier_id,supplier_no,name,contact_name,contact_phone,contact_email,street_address,region,post_code,remark from supplier where is_deleted=0 and supplier_id=#{supplier_id}")
    Supplier selectBySupplierId(String supplier_id);

    @Update("update supplier set supplier_no=#{supplier_no},name=#{name},contact_name=#{contact_name},contact_phone=#{contact_phone},contact_email=#{contact_email},street_address=#{street_address},region=#{region},post_code=#{post_code},remark=#{remark}, last_updated_user_id=#{last_updated_user_id},last_updated_user_name=#{last_updated_user_name},last_updated_date_time=#{last_updated_date_time} where supplier_id=#{supplier_id}")
    void saveSupplier(Supplier supplier);

    //soft delete
    @Update("update supplier set is_deleted=1 where supplier_id=#{supplier_id}")
    void deleteBySupplierId(String supplier_id);
}
