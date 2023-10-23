package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.Material;
import com.zj.wms.pojo.PurchaseItem;
import com.zj.wms.pojo.User;
import com.zj.wms.service.MaterialService;
import com.zj.wms.service.PurchaseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class PurchaseItemController {
    @Autowired
    PurchaseItemService purchaseItemService;
    @Autowired
    MaterialService materialService;

    @RequestMapping("purchaseItemList")
    public String purchaseItemList(Model model, @RequestParam(value="keywords_no", defaultValue="") String keywords, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size,@RequestParam(value="purchase_no") String purchase_no){
        PageHelper.startPage(start, size, "display_order asc");
        List<PurchaseItem> purchaseItemList =purchaseItemService.selectPurchaseItemByKeywords(keywords,purchase_no);
        PageInfo<PurchaseItem> page = new PageInfo<>(purchaseItemList);
        model.addAttribute("page", page);
        model.addAttribute("keywords_no", keywords);
        model.addAttribute("purchase_no", purchase_no);
        return "purchaseItemList";
    }
    //选择物料
    @RequestMapping("chooseMaterial")
    public String AddPurchaseItem(String purchase_no, Model m, @RequestParam(value="material_type_id", defaultValue="") String material_type_id, @RequestParam(value="keywords_no", defaultValue="") String keywords_no, @RequestParam(value="keywords_name", defaultValue="") String keywords_name , @RequestParam(value="keywords_specifications", defaultValue="") String keywords_specifications, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size){
        m.addAttribute("purchase_no",purchase_no);
        PageHelper.startPage(start, size, "display_order asc");
        List<Material> materials = materialService.getMaterialsByKeywordsAndMaterialId(material_type_id, keywords_no, keywords_name, keywords_specifications);
        PageInfo<Material> page = new PageInfo<>(materials);
        m.addAttribute("page", page);
        m.addAttribute("material_type_id", material_type_id);
        m.addAttribute("keywords_no", keywords_no);
        m.addAttribute("keywords_name", keywords_name);
        m.addAttribute("keywords_specifications", keywords_specifications);
        return "chooseMaterial";
    }
    @RequestMapping("addToPurchase")
    public String AddPurchaseItemCommit(Model m,HttpSession hs, String purchase_no, PurchaseItem purchaseItem){
        purchaseItem.setPurchase_item_id(UUID.randomUUID().toString());
        int display_order = purchaseItemService.getNewDisplayOrder();
        String purchase_item_no = "purchaseItem" + String.format("%05d", display_order);
        purchaseItem.setDisplay_order(display_order);
        purchaseItem.setPurchase_item_no(purchase_item_no);
        purchaseItem.setPurchase_no(purchase_no);
        purchaseItem.setTotal_price(purchaseItem.getPrice().multiply(purchaseItem.getQuantity()));
        //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
        User u = (User) hs.getAttribute("user");
        purchaseItem.setCreated_user_id(u.getUser_id());
        purchaseItem.setCreated_user_name(u.getName());
        purchaseItem.setCreated_date_time(new Date());
        purchaseItem.setLast_updated_user_id(u.getUser_id());
        purchaseItem.setLast_updated_user_name(u.getName());
        purchaseItem.setLast_updated_date_time(new Date());
        //保存添加
        purchaseItemService.addPurchaseItem(purchaseItem);
        return "redirect:purchaseItemList?purchase_no=" + purchase_no;
    }
    //修改清单
    @RequestMapping("editPurchaseItem")
    public String editPurchaseItem(String purchase_item_id,Model model){
        model.addAttribute("purchase_item_id",purchase_item_id);
        return "editPurchaseItem";
    }
    @RequestMapping("editPurchaseItemCommit")
    public String editPurchaseItemCommit(String purchase_item_id, BigDecimal quantity){
        //保存修改
        purchaseItemService.editPurchaseItem(purchase_item_id, quantity);
        return "editSuccess";
    }
    //删除
    @RequestMapping("deletePurchaseItem")
    public String editPurchaseItemCommit(Model m, String purchase_no, String purchase_item_id){
        purchaseItemService.deletePurchaseItemById(purchase_item_id);
        m.addAttribute("purchase_no", purchase_no);
        return "forward:purchaseItemList";
    }
}