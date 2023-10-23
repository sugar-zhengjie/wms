package com.zj.wms.service;

import com.zj.wms.mapper.CustomerMapper;
import com.zj.wms.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    //获得所有客户
    public List<Customer> getAllCustomers() {
        return customerMapper.getAllCustomers();
    }

    //根据关键字获得符合条件的客户
    public List<Customer> getCustomersByKeywords(String keywords) {
        return customerMapper.getCustomersByKeywords(keywords);
    }

    //根据id查询客户
    public Customer selectByCustomerId(String customer_id) {
        return customerMapper.selectByCustomerId(customer_id);
    }

    //保存修改客户
    public void saveCustomer(Customer customer) {
        customerMapper.saveCustomer(customer);
    }

    //soft delete删除客户
    public void deleteByCustomerId(String customer_id) {
        customerMapper.deleteByCustomerId(customer_id);
    }

    //信用客户
    public void addCustomer(Customer customer) {
        customerMapper.addCustomer(customer);
    }

    //获得最大客户排序+1
    public int getNewDisplayOrder() {
        Integer maxDisplayOrder = customerMapper.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }
}
