package com.zj.wms.mapper;

import com.zj.wms.pojo.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CustomerMapper {

    @Select("select customer_id,customer_no,name,contact_name,contact_phone,contact_email,street_address,region,post_code,remark from customer where is_deleted=0")
    List<Customer> getAllCustomers();

    @Select("select customer_id,customer_no,name,contact_name,contact_phone,contact_email,street_address,region,post_code,remark from customer where is_deleted=0 and name like '%${keywords}%'")
    List<Customer> getCustomersByKeywords(String keywords);

    @Select("select customer_id,customer_no,name,contact_name,contact_phone,contact_email,street_address,region,post_code,remark from customer where is_deleted=0 and customer_id=#{customer_id}")
    Customer selectByCustomerId(String customer_id);

    @Update("update customer set customer_no=#{customer_no},name=#{name},contact_name=#{contact_name},contact_phone=#{contact_phone},contact_email=#{contact_email},street_address=#{street_address},region=#{region},post_code=#{post_code},remark=#{remark}, last_updated_user_id=#{last_updated_user_id},last_updated_user_name=#{last_updated_user_name},last_updated_date_time=#{last_updated_date_time} where customer_id=#{customer_id}")
    void saveCustomer(Customer customer);

    @Delete("update customer set is_deleted=1 where customer_id=#{customer_id}")
    void deleteByCustomerId(String customer_id);

    @Insert("insert into customer(customer_id,customer_no,name,contact_name,contact_phone,contact_email,street_address,region,post_code,remark,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,display_order) values(#{customer_id},#{customer_no},#{name},#{contact_name},#{contact_phone},#{contact_email},#{street_address},#{region},#{post_code},#{remark},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{display_order})")
    void addCustomer(Customer customer);

    @Select("select max(display_order) from customer")
    Integer selectMaxDisplayOrder();
}
