package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.Purchase;
import com.zj.wms.pojo.User;
import com.zj.wms.service.PurchaseService;
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
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @RequestMapping("purchaseList")
    public String purchaseList(Model model, @RequestParam(value="keywords_no", defaultValue="") String keywords, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size){
        PageHelper.startPage(start, size, "display_order asc");
        List<Purchase> purchaseList =purchaseService.selectPurchaseByKeywords(keywords);
        PageInfo<Purchase> page = new PageInfo<>(purchaseList);
        model.addAttribute("page", page);
        model.addAttribute("keywords_no", keywords);
        return "purchaseList";
    }
    @RequestMapping("addPurchase")
    public String addPurchase(HttpSession hs){
        Purchase purchase=new Purchase();
        purchase.setPurchase_id(UUID.randomUUID().toString());
        int display_order = purchaseService.getNewDisplayOrder();
        purchase.setDisplay_order(display_order);
        String purchase_no = "purchase" + String.format("%05d", display_order);
        purchase.setPurchase_no(purchase_no);
        //此处添加从session中获取用户
        User u = (User) hs.getAttribute("user");
        purchase.setApply_organization_id(u.getUser_id());
        purchase.setCreated_user_id(u.getUser_id());
        purchase.setCreated_user_name(u.getName());
        purchase.setCreated_date_time(new Date());
        purchase.setLast_updated_user_id(u.getUser_id());
        purchase.setLast_updated_user_name(u.getName());
        purchase.setLast_updated_date_time(new Date());
        purchaseService.addPurchase(purchase);
        return "addSuccess";
    }
}