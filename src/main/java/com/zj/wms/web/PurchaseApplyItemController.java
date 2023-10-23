package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.PurchaseApplyItem;
import com.zj.wms.pojo.PurchaseReceiveItem;
import com.zj.wms.pojo.User;
import com.zj.wms.service.PurchaseApplyItemService;
import com.zj.wms.service.PurchaseReceiveItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Controller
public class PurchaseApplyItemController {
    @Autowired
    PurchaseApplyItemService purchaseApplyItemService;
    @Autowired
    PurchaseReceiveItemService purchaseReceiveItemService;

    @RequestMapping("purchaseApplyItemList")
    public String purchaseApplyItemList(HttpSession hs, String purchase_no, String purchase_apply_no, String purchase_receive_no, Model model, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size) {
        PageHelper.startPage(start, size, "display_order asc");
        List<PurchaseApplyItem> purchaseApplyItemList = purchaseApplyItemService.getByApplyNo(purchase_apply_no);
        if (purchaseApplyItemList == null || purchaseApplyItemList.size()<= 0) {
            List<PurchaseReceiveItem> purchaseReceiveItemList = purchaseReceiveItemService.getAllItem(purchase_receive_no);
            purchaseApplyItemList = new LinkedList<PurchaseApplyItem>();
            for (PurchaseReceiveItem item : purchaseReceiveItemList) {
                PurchaseApplyItem apply_item = new PurchaseApplyItem();
                apply_item.setPurchase_apply_item_id(UUID.randomUUID().toString());
                int display_order = purchaseApplyItemService.getNewDisplayOrder();
                apply_item.setDisplay_order(display_order);
                apply_item.setPurchase_apply_item_no("purchaseApplyItem" + String.format("%05d", display_order));
                apply_item.setPurchase_apply_no(purchase_apply_no);
                apply_item.setPurchase_no(purchase_no);
                apply_item.setMatnr(item.getMatnr());
                apply_item.setQuantity(item.getQuantity());
                //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
                User u = (User) hs.getAttribute("user");
                apply_item.setCreated_user_id(u.getUser_id());
                apply_item.setCreated_user_name(u.getName());
                apply_item.setCreated_date_time(new Date());
                apply_item.setLast_updated_user_id(u.getUser_id());
                apply_item.setLast_updated_user_name(u.getName());
                apply_item.setLast_updated_date_time(new Date());

                purchaseApplyItemList.add(apply_item);
                purchaseApplyItemService.addItem(apply_item);
            }
        }
        PageInfo<PurchaseApplyItem> page = new PageInfo<>(purchaseApplyItemList);
        model.addAttribute("page", page);
        model.addAttribute("purchase_apply_no", purchase_apply_no);
        model.addAttribute("purchase_no", purchase_no);
        model.addAttribute("purchase_receive_no", purchase_receive_no);
        return "purchaseApplyItemList";
    }

    @RequestMapping("editPurchaseApplyItem")
    public String editPurchaseApplyItem(String purchase_apply_item_id, String location_no){
        purchaseApplyItemService.editLocation(purchase_apply_item_id, location_no);
        return "editSuccess";
    }


}
