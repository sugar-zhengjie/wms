package com.zj.wms.web;

import com.zj.wms.pojo.User;
import com.zj.wms.pojo.Warehouse;
import com.zj.wms.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Controller
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @RequestMapping("addWarehouse")
    public String addWarehouse(String werks_id, Model m) {
        m.addAttribute("werks_id", werks_id);
        return "addWarehouse";
    }

    @RequestMapping("addWarehouseCommit")
    public String addWarehouseCommit(HttpSession hs, Warehouse warehouse) {
        warehouse.setWarehouse_id(UUID.randomUUID().toString());
        int display_order = warehouseService.getNewDisplayOrder();
        warehouse.setDisplay_order(display_order);
        //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
        User u = (User) hs.getAttribute("user");
        warehouse.setCreated_user_id(u.getUser_id());
        warehouse.setCreated_user_name(u.getName());
        warehouse.setCreated_date_time(new Date());
        warehouse.setLast_updated_user_id(u.getUser_id());
        warehouse.setLast_updated_user_name(u.getName());
        warehouse.setLast_updated_date_time(new Date());
        warehouseService.addWarehouse(warehouse);
        return "addSuccess";
    }

    @RequestMapping("editWarehouse")
    public String editWarehouse(Model m, String warehouse_id) {
        Warehouse warehouse = warehouseService.selectByWarehouseId(warehouse_id);
        m.addAttribute("onEditWarehouse", warehouse);
        return "editWarehouse";
    }

    @RequestMapping("editWarehouseCommit")
    public String editWarehouseCommit(HttpSession hs, Warehouse warehouse) {
        //从Session中获取用户, 嵌入最后更新人相关字段
        User u = (User) hs.getAttribute("user");
        warehouse.setLast_updated_user_id(u.getUser_id());
        warehouse.setLast_updated_user_name(u.getName());
        warehouse.setLast_updated_date_time(new Date());

        warehouseService.saveWarehouse(warehouse);
        return "editSuccess";
    }

    //soft delete
    @RequestMapping("deleteWarehouse")
    public String deleteWarehouse(String warehouse_id) {
        warehouseService.deleteByWarehouseId(warehouse_id);
        return "forward:locationTree";
    }
}
