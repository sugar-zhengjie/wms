package com.zj.wms.service;

import com.zj.wms.mapper.PurchaseApplyItemMapper;
import com.zj.wms.pojo.PurchaseApplyItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseApplyItemService {
    @Autowired
    PurchaseApplyItemMapper purchaseApplyItemMapper;


    public Integer getNewDisplayOrder() {
        Integer maxDisplayOrder = purchaseApplyItemMapper.getMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addItem(PurchaseApplyItem apply_item) {
        purchaseApplyItemMapper.addItem(apply_item);
    }

    public void editLocation(String purchase_apply_item_id, String location_no) {
        purchaseApplyItemMapper.editLocation(purchase_apply_item_id, location_no);
    }

    public List<PurchaseApplyItem> getAllItem(String purchase_apply_no) {
        return purchaseApplyItemMapper.getAllItem(purchase_apply_no);
    }

    public List<PurchaseApplyItem> getByApplyNo(String purchase_apply_no) {
        return purchaseApplyItemMapper.getByApplyNo(purchase_apply_no);
    }
}
