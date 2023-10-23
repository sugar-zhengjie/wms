package com.zj.wms.web;

import com.zj.wms.pojo.Shelves;
import com.zj.wms.pojo.User;
import com.zj.wms.service.PurchaseReceiveService;
import com.zj.wms.service.ShelvesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Controller
public class ShelvesController {
    @Autowired
    ShelvesService shelvesService;
    @Autowired
    PurchaseReceiveService purchaseReceiveService;

    @RequestMapping("addShelves")
    public String addShelves(HttpSession hs, String purchase_no, String purchase_apply_no, String purchase_receive_no) {
        purchaseReceiveService.closePurchaseReceive(purchase_receive_no);
        Shelves shelves = new Shelves();
        shelves.setShelves_id(UUID.randomUUID().toString());
        int display_order = shelvesService.getNewDisplayOrder();
        shelves.setDisplay_order(display_order);
        shelves.setShelves_no("shelves" + String.format("%05d", display_order));
        shelves.setType(1);
        shelves.setPurchase_apply_no(purchase_apply_no);
        shelves.setPurchase_no(purchase_no);
        //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
        User u = (User) hs.getAttribute("user");
        shelves.setCreated_user_id(u.getUser_id());
        shelves.setCreated_user_name(u.getName());
        shelves.setCreated_date_time(new Date());
        shelves.setLast_updated_user_id(u.getUser_id());
        shelves.setLast_updated_user_name(u.getName());
        shelves.setLast_updated_date_time(new Date());

        shelvesService.addShelves(shelves);
        return "redirect:addShelvesItem?purchase_apply_no=" + purchase_apply_no + "&shelves_no=" + shelves.getShelves_no();
    }

}
