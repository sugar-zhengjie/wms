package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.InventoryType;
import com.zj.wms.pojo.User;
import com.zj.wms.service.InventoryTypeService;
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
public class InventoryTypeController {
    @Autowired
    InventoryTypeService inventoryTypeService;

    @RequestMapping("inventoryTypeList")
    public String inventoryTypeList(Model m, @RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size){
        PageHelper.startPage(start, size, "display_order asc");
        List<InventoryType> inventoryTypes = inventoryTypeService.getInventoryTypesByKeywords(keywords);
        PageInfo<InventoryType> page = new PageInfo<>(inventoryTypes);
        m.addAttribute("page", page);
        m.addAttribute("keywords", keywords);
        return "inventoryTypeList";
    }

    @RequestMapping("addInventoryType")
    public String addInventoryType(){
        return "addInventoryType";
    }

    @RequestMapping("addInventoryTypeCommit")
    public String addInventoryTypeCommit(HttpSession hs, InventoryType inventoryType) {
        int display_order = inventoryTypeService.getNewDisplayOrder();
        String inventory_type_id = UUID.randomUUID().toString();
        inventoryType.setInventory_type_id(inventory_type_id);
        inventoryType.setDisplay_order(display_order);
        //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
        User u = (User) hs.getAttribute("user");
        inventoryType.setCreated_user_id(u.getUser_id());
        inventoryType.setCreated_user_name(u.getName());
        inventoryType.setCreated_date_time(new Date());
        inventoryType.setLast_updated_user_id(u.getUser_id());
        inventoryType.setLast_updated_user_name(u.getName());
        inventoryType.setLast_updated_date_time(new Date());
        inventoryTypeService.addInventoryType(inventoryType);
        return "addSuccess";
    }

    @RequestMapping("editInventoryType")
    public String editInventoryType(Model m, String inventory_type_id) {
        InventoryType inventoryType = inventoryTypeService.selectByInventoryTypeId(inventory_type_id);
        m.addAttribute("onEditInventoryType", inventoryType);
        return "editInventoryType";
    }

    @RequestMapping("editInventoryTypeCommit")
    public String editInventoryTypeCommit(HttpSession hs, InventoryType inventoryType) {
        //从Session中获取用户, 嵌入最后更新人相关字段
        User u = (User) hs.getAttribute("user");
        inventoryType.setLast_updated_user_id(u.getUser_id());
        inventoryType.setLast_updated_user_name(u.getName());
        inventoryType.setLast_updated_date_time(new Date());

        inventoryTypeService.saveInventoryType(inventoryType);
        return "editSuccess";
    }

    //soft delete
    @RequestMapping("deleteInventoryType")
    public String deleteInventoryType(String inventory_type_id) {
        inventoryTypeService.deleteByInventoryTypeId(inventory_type_id);
        return "forward:inventoryTypeList";
    }


}
