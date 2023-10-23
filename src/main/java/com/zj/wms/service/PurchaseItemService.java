package com.zj.wms.service;

import com.zj.wms.mapper.PurchaseItemMapper;
import com.zj.wms.pojo.PurchaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PurchaseItemService {
    @Autowired
    PurchaseItemMapper purchaseItemMapper;

    //查询所有清单
    public List<PurchaseItem> selectAllPurchaseItem(){return purchaseItemMapper.selectAllPurchaseItem();}

    //根据清单id查询具体清单
    public PurchaseItem selectPurchaseItemById(String purchase_item_id){return purchaseItemMapper.selectPurchaseItemById(purchase_item_id);}

    //根据订单ID查询对应的清单
    public List<PurchaseItem> selectPurchaseItemListById(String purchase_id){return purchaseItemMapper.selectPurchaseItemListById(purchase_id);}

    //根据订单编号查询对应清单
    public List<PurchaseItem> selectPurchaseItemListByNo(String purchase_no){return purchaseItemMapper.selectPurchaseItemListByNo(purchase_no);}

    public void deletePurchaseItemById(String purchase_item_id){purchaseItemMapper.deletePurchaseItemById(purchase_item_id);}

    //根据keyword和订单编号查询
    public List<PurchaseItem> selectPurchaseItemByKeywords(String keywords,String purchase_no){
        return purchaseItemMapper.selectPurchaseItemByKeywords(keywords,purchase_no);
    }

    //获得新排序编号
    public int getNewDisplayOrder() {
        Integer maxDisplayOrder = purchaseItemMapper.getMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addPurchaseItem(PurchaseItem purchaseItem) {
        purchaseItemMapper.addPurchaseItem(purchaseItem);
    }

    public void editPurchaseItem(String purchase_item_id, BigDecimal quantity) {
        purchaseItemMapper.editPurchaseItem(purchase_item_id, quantity);
    }

    public List<PurchaseItem> selectAllPurchaseItemNotReceiveByNo(String purchase_no){return purchaseItemMapper.selectAllPurchaseItemNotReceiveByNo(purchase_no);};

    public void editPurchaseItemReceive(BigDecimal newReceivedQuantity,String purchase_item_no){
        purchaseItemMapper.editPurchaseItemReceive(newReceivedQuantity,purchase_item_no);
    }

    public BigDecimal selectQuantity(String purchase_item_no){return purchaseItemMapper.selectQuantity(purchase_item_no);}

    public BigDecimal selectInitReceivedQuantity(String purchase_item_no){return purchaseItemMapper.selectInitReceivedQuantity(purchase_item_no);}

    public PurchaseItem getByNo(String purchase_item_no) {
        return purchaseItemMapper.getByNo(purchase_item_no);
    }
}