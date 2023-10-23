package com.zj.wms.web;

import com.zj.wms.pojo.PurchaseApplyItem;
import com.zj.wms.pojo.ShelvesItem;
import com.zj.wms.pojo.User;
import com.zj.wms.service.PurchaseApplyItemService;
import com.zj.wms.service.ShelvesItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ShelvesItemController {
    @Autowired
    ShelvesItemService shelvesItemService;
    @Autowired
    PurchaseApplyItemService purchaseApplyItemService;

    @RequestMapping("addShelvesItem")
    public String addShelvesItem(HttpSession hs, String purchase_apply_no, String shelves_no) {
        List<PurchaseApplyItem> items = purchaseApplyItemService.getAllItem(purchase_apply_no);
        for (PurchaseApplyItem item : items) {
            ShelvesItem shelves_item = new ShelvesItem();
            shelves_item.setShelves_item_id(UUID.randomUUID().toString());
            int display_order = shelvesItemService.getNewDisplayOrder();
            shelves_item.setDisplay_order(display_order);
            shelves_item.setShelves_item_no("shelvesItem" + String.format("%05d", display_order));
            shelves_item.setShelves_no(shelves_no);
            shelves_item.setLocation_no(item.getLocation_no());
            shelves_item.setMatnr(item.getMatnr());
            shelves_item.setQuantity(item.getQuantity());
            //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
            User u = (User) hs.getAttribute("user");
            shelves_item.setCreated_user_id(u.getUser_id());
            shelves_item.setCreated_user_name(u.getName());
            shelves_item.setCreated_date_time(new Date());
            shelves_item.setLast_updated_user_id(u.getUser_id());
            shelves_item.setLast_updated_user_name(u.getName());
            shelves_item.setLast_updated_date_time(new Date());

            shelvesItemService.addShelvesItem(shelves_item);
        }
        return "redirect:purchaseReceiveList";
    }


}
