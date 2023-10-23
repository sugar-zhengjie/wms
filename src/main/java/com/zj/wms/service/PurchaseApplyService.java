package com.zj.wms.service;

import com.zj.wms.mapper.PurchaseApplyMapper;
import com.zj.wms.pojo.PurchaseApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseApplyService {
    @Autowired
    PurchaseApplyMapper purchaseApplyMapper;

    public int getNewDisplayOrder() {
        Integer maxDisplayOrder = purchaseApplyMapper.getMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addPurchaseApply(PurchaseApply purchaseApply) {
        purchaseApplyMapper.addPurchaseApply(purchaseApply);
    }
}
