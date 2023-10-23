package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.Supplier;
import com.zj.wms.pojo.User;
import com.zj.wms.service.SupplierService;
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
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @RequestMapping("supplierList")
    public String supplierList(Model m, @RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size) {
        PageHelper.startPage(start, size, "display_order asc");
        List<Supplier> suppliers = supplierService.getSuppliersByKeywords(keywords);
        PageInfo<Supplier> page = new PageInfo<>(suppliers);
        m.addAttribute("page", page);
        m.addAttribute("keywords", keywords);
        return "supplierList";
    }

    @RequestMapping("addSupplier")
    public String addSupplier() {
        return "addSupplier";
    }

    @RequestMapping("addSupplierCommit")
    public String addSupplierCommit(HttpSession hs, Supplier supplier) {
        int display_order = supplierService.getNewDisplayOrder();
        String supplier_id = UUID.randomUUID().toString();
        supplier.setSupplier_id(supplier_id);
        supplier.setDisplay_order(display_order);
        //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
        User u = (User) hs.getAttribute("user");
        supplier.setCreated_user_id(u.getUser_id());
        supplier.setCreated_user_name(u.getName());
        supplier.setCreated_date_time(new Date());
        supplier.setLast_updated_user_id(u.getUser_id());
        supplier.setLast_updated_user_name(u.getName());
        supplier.setLast_updated_date_time(new Date());

        supplierService.addSupplier(supplier);
        return "addSuccess";
    }

    @RequestMapping("editSupplier")
    public String editSupplier(Model m, String supplier_id) {
        Supplier supplier = supplierService.selectBySupplierId(supplier_id);
        m.addAttribute("onEditSupplier", supplier);
        return "editSupplier";
    }

    @RequestMapping("editSupplierCommit")
    public String editSupplierCommit(HttpSession hs, Supplier supplier) {
        //从Session中获取用户, 嵌入最后更新人相关字段
        User u = (User) hs.getAttribute("user");
        supplier.setLast_updated_user_id(u.getUser_id());
        supplier.setLast_updated_user_name(u.getName());
        supplier.setLast_updated_date_time(new Date());

        supplierService.saveSupplier(supplier);
        return "editSuccess";
    }

    @RequestMapping("deleteSupplier")
    public String deleteSupplier(String supplier_id) {
        supplierService.deleteBySupplierId(supplier_id);
        return "forward:supplierList";
    }

}
