package com.zj.wms.service;

import com.zj.wms.mapper.PurchaseReceiveItemMapper;
import com.zj.wms.pojo.PurchaseReceiveItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseReceiveItemService {
    @Autowired
    PurchaseReceiveItemMapper purchaseReceiveItemMapper;

    //获得最大排序+1
    public int getNewDisplayOrder() {
        Integer maxDisplayOrder = purchaseReceiveItemMapper.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addPurchaseReceiveItem(PurchaseReceiveItem purchaseReceiveItem){purchaseReceiveItemMapper.addPurchaseReceiveItem(purchaseReceiveItem);}

    public List<PurchaseReceiveItem> getAllItem(String purchase_receive_no) {
        return purchaseReceiveItemMapper.getAllItem(purchase_receive_no);
    }
}