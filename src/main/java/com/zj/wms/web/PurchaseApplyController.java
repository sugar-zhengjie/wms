package com.zj.wms.web;

import com.zj.wms.pojo.PurchaseApply;
import com.zj.wms.pojo.User;
import com.zj.wms.service.PurchaseApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Controller
public class PurchaseApplyController {
    @Autowired
    PurchaseApplyService purchaseApplyService;

    @RequestMapping("addPurchaseApply")
    public String addPurchaseApply(HttpSession hs, String purchase_receive_no, String purchase_no) {
        PurchaseApply purchaseApply = new PurchaseApply();
        purchaseApply.setPurchase_no(purchase_no);
        purchaseApply.setPurchase_receive_no(purchase_receive_no);
        purchaseApply.setPurchase_apply_id(UUID.randomUUID().toString());
        int display_order = purchaseApplyService.getNewDisplayOrder();
        purchaseApply.setDisplay_order(display_order);
        String purchase_apply_no = "purchaseApply" + String.format("%05d", display_order);
        purchaseApply.setPurchase_apply_no(purchase_apply_no);
        //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
        User u = (User) hs.getAttribute("user");
        purchaseApply.setCreated_user_id(u.getUser_id());
        purchaseApply.setCreated_user_name(u.getName());
        purchaseApply.setCreated_date_time(new Date());
        purchaseApply.setLast_updated_user_id(u.getUser_id());
        purchaseApply.setLast_updated_user_name(u.getName());
        purchaseApply.setLast_updated_date_time(new Date());

        purchaseApplyService.addPurchaseApply(purchaseApply);
        return "redirect:purchaseApplyItemList?purchase_apply_no=" + purchase_apply_no + "&purchase_no=" + purchase_no + "&purchase_receive_no=" + purchase_receive_no;
    }

}
