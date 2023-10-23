package com.zj.wms.service;

import com.zj.wms.mapper.PurchaseReceiveMapper;
import com.zj.wms.pojo.PurchaseReceive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseReceiveService {
    @Autowired
    PurchaseReceiveMapper purchaseReceiveMapper;
    //根据purchase_no查询对应的收货单
    public  PurchaseReceive selectPurchaseReceiveByPurchaseNo(String purchase_no){return purchaseReceiveMapper.selectPurchaseReceiveByPurchaseNo(purchase_no);}
    //获得最大排序+1
    public int getNewDisplayOrder() {
        Integer maxDisplayOrder = purchaseReceiveMapper.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }
    //创建收货单
    public void addPurchaseReceive(PurchaseReceive purchaseReceive){ purchaseReceiveMapper.addPurchaseReceive(purchaseReceive);}

    public List<PurchaseReceive> selectAllPurchaseReceive(){return purchaseReceiveMapper.selectAllPurchaseReceive();}

    public void closePurchaseReceive(String purchase_receive_no) {
        purchaseReceiveMapper.closePurchaseReceive(purchase_receive_no);
    }

    public void commit(String purchase_receive_no) {
        purchaseReceiveMapper.commit(purchase_receive_no);
    }
}