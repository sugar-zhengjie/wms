package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.Customer;
import com.zj.wms.pojo.User;
import com.zj.wms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping("customerList")
    public String customerList(Model m, @RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size){
        PageHelper.startPage(start, size, "display_order asc");
        List<Customer> customers = customerService.getCustomersByKeywords(keywords);
        PageInfo<Customer> page = new PageInfo<>(customers);
        m.addAttribute("page", page);
        m.addAttribute("keywords", keywords);
        return "customerList";
    }

    @RequestMapping("editCustomer")
    public String editCustomer(Model m, String customer_id){
        Customer customer = customerService.selectByCustomerId(customer_id);
        m.addAttribute("onEditCustomer", customer);
        return "editCustomer";
    }

    @RequestMapping("editCustomerCommit")
    public String editCustomerCommit(HttpSession hs, Customer customer) {
        //此处添加从session中获取用户, 设置customer的最后更新人id和name字段, 以及最后更新时间
        User u = (User) hs.getAttribute("user");
        customer.setLast_updated_user_id(u.getUser_id());
        customer.setLast_updated_user_name(u.getName());
        customer.setLast_updated_date_time(new Date());
        customerService.saveCustomer(customer);
        return "editSuccess";

    }

    @RequestMapping("deleteCustomer")
    public String deleteCustomer(String customer_id) {
        customerService.deleteByCustomerId(customer_id);
        return "forward:customerList";
    }

    @RequestMapping("addCustomer")
    public String  addCustomer() {
        return "addCustomer";
    }

    @RequestMapping("addCustomerCommit")
    public String  addCustomerCommit(HttpSession hs, Customer customer) {
        int display_order = customerService.getNewDisplayOrder();
        String customer_id = UUID.randomUUID().toString();
        customer.setDisplay_order(display_order);
        customer.setCustomer_id(customer_id);
        //此处添加从session中获取用户, 设置customer的创建人id和name字段以及创建时间字段, 这里仅用admin账户示例
        User u = (User) hs.getAttribute("user");
        customer.setCreated_user_id(u.getUser_id());
        customer.setCreated_user_name(u.getName());
        customer.setCreated_date_time(new Date());
        customer.setLast_updated_user_id(u.getUser_id());
        customer.setLast_updated_user_name(u.getName());
        customer.setLast_updated_date_time(new Date());
        customerService.addCustomer(customer);
        return "addSuccess";
    }

}