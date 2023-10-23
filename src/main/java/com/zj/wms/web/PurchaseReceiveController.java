package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.PurchaseItem;
import com.zj.wms.pojo.PurchaseReceive;
import com.zj.wms.pojo.PurchaseReceiveItem;
import com.zj.wms.pojo.User;
import com.zj.wms.service.PurchaseItemService;
import com.zj.wms.service.PurchaseReceiveItemService;
import com.zj.wms.service.PurchaseReceiveService;
import com.zj.wms.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Controller
public class PurchaseReceiveController {
    @Autowired
    PurchaseReceiveService purchaseReceiveService;
    @Autowired
    PurchaseReceiveItemService purchaseReceiveItemService;
    @Autowired
    PurchaseItemService purchaseItemService;
    @Autowired
    PurchaseService purchaseService;

    @RequestMapping("addPurchaseReceive")
    public String addPurchaseReceive(HttpSession hs,String purchase_no){
        PurchaseReceive purchaseReceive=new PurchaseReceive();
            //生成收货单
            int display_order = purchaseReceiveService.getNewDisplayOrder();
            String purchase_receive_no = "purchase_receive" + String.format("%05d", display_order);
            purchaseReceive.setPurchase_receive_id(UUID.randomUUID().toString());
            purchaseReceive.setPurchase_receive_no(purchase_receive_no);
            purchaseReceive.setDisplay_order(display_order);
            purchaseReceive.setPurchase_no(purchase_no);
            //此处添加从session中获取用户
            User u = (User) hs.getAttribute("user");
            purchaseReceive.setCreated_user_id(u.getUser_id());
            purchaseReceive.setCreated_user_name(u.getName());
            purchaseReceive.setCreated_date_time(new Date());
            purchaseReceive.setLast_updated_user_id(u.getUser_id());
            purchaseReceive.setLast_updated_user_name(u.getName());
            purchaseReceive.setLast_updated_date_time(new Date());
            purchaseReceive.setAudit_user_id(u.getUser_id());
            purchaseReceive.setOpt_user_id(u.getUser_id());
            purchaseReceive.setState(1);
            purchaseReceiveService.addPurchaseReceive(purchaseReceive);
            hs.setAttribute("purchaseReceive", purchaseReceive);
        return "redirect:purchaseReceiveItemList?purchase_no="+purchase_no;
    }

    @RequestMapping("purchaseReceiveItemList")
    public String purchaseReceiveList(HttpSession hs, Model model,@RequestParam(value="purchase_no") String purchase_no, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "10") int size){
        PurchaseReceive purchaseReceive= (PurchaseReceive) hs.getAttribute("purchaseReceive");
        //收货清单列表
        PageHelper.startPage(start, size, "display_order asc");
        List<PurchaseItem> purchaseItemList=purchaseItemService.selectAllPurchaseItemNotReceiveByNo(purchase_no);
        PageInfo<PurchaseItem> page = new PageInfo<>(purchaseItemList);
        model.addAttribute("page", page);
        model.addAttribute("purchaseReceive",purchaseReceive);
        return "purchaseReceiveItemList";
    }

    @RequestMapping(value = "addPurchaseReceiveItemList", produces = "text/html;charset=UTF-8")
    public String addPurchaseReceiveItemList(@RequestParam Map<String,String> name,HttpSession hs){
        Set<String> set=name.keySet();
        String purchase_receive_no=null;
        String purchase_no=null;
        PurchaseReceiveItem purchaseReceiveItem=new PurchaseReceiveItem();
        //此处添加从session中获取用户
        User u = (User) hs.getAttribute("user");
        for(String key:set){
            String[] Str=key.split(",");
            String purchase_item_no=Str[0];
            purchase_receive_no=Str[1];
            purchase_no=Str[2];
            String str = name.get(key);
            if (str == null || "".equals(str)) {
                str = "0";
            }
            BigDecimal init_received_quantity=new BigDecimal(str);
            //修改订单清单数量
            //原有清单收货数量
            BigDecimal b=purchaseItemService.selectInitReceivedQuantity(purchase_item_no);
            BigDecimal newReceivedQuantity = b.add(init_received_quantity);
            purchaseItemService.editPurchaseItemReceive(newReceivedQuantity,purchase_item_no);
            //生成收货清单
            int display_order = purchaseReceiveItemService.getNewDisplayOrder();
            String purchase_receive_item_no = "purchase_receive_item" + String.format("%05d", display_order);
            purchaseReceiveItem.setPurchase_receive_item_id(UUID.randomUUID().toString());
            purchaseReceiveItem.setPurchase_receive_item_no(purchase_receive_item_no);
            purchaseReceiveItem.setPurchase_receive_no(purchase_receive_no);
            purchaseReceiveItem.setPurchase_item_no(purchase_item_no);
            purchaseReceiveItem.setPurchase_no(purchase_no);
            purchaseReceiveItem.setDisplay_order(display_order);
            purchaseReceiveItem.setQuantity(init_received_quantity);
            //此处添加从session中获取用户
            purchaseReceiveItem.setCreated_user_id(u.getUser_id());
            purchaseReceiveItem.setCreated_user_name(u.getName());
            purchaseReceiveItem.setCreated_date_time(new Date());
            purchaseReceiveItem.setLast_updated_user_id(u.getUser_id());
            purchaseReceiveItem.setLast_updated_user_name(u.getName());
            purchaseReceiveItem.setLast_updated_date_time(new Date());
            purchaseReceiveItem.setAudit_user_id(u.getUser_id());
            //设置物料编号
            PurchaseItem pi = purchaseItemService.getByNo(purchase_item_no);
            purchaseReceiveItem.setMatnr(pi.getMatnr());
            purchaseReceiveItemService.addPurchaseReceiveItem(purchaseReceiveItem);
        }
        purchaseReceiveService.commit(purchase_receive_no);
            return "forward:purchaseList";
    }
    @RequestMapping("purchaseReceiveList")
    public String purchaseReceiveList(Model model,@RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size){
        PageHelper.startPage(start, size, "display_order asc");
        List<PurchaseReceive> purchaseReceiveList=purchaseReceiveService.selectAllPurchaseReceive();
        PageInfo<PurchaseReceive> page = new PageInfo<>(purchaseReceiveList);
        model.addAttribute("page", page);
        model.addAttribute("keywords", keywords);
        return "purchaseReceiveList";
    }
}