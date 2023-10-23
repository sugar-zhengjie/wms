package com.zj.wms.service;

import com.zj.wms.mapper.PurchaseMapper;
import com.zj.wms.pojo.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    PurchaseMapper purchaseMapper;

    //查询所有订单
    public List<Purchase> selectAllPurchase(){return purchaseMapper.selectAllPurchase();}
    //根据id查找订单
    public Purchase selectPurchaseByPurchaseId(String purchase_id){return purchaseMapper.selectPurchaseByPurchaseId(purchase_id);}
    //根据订单号查询
    public List<Purchase> selectPurchaseByKeywords(String keywords){return purchaseMapper.selectPurchaseByKeywords(keywords);}
    //获得最大排序+1
    public int getNewDisplayOrder() {
        Integer maxDisplayOrder = purchaseMapper.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }
    //新增订单
    public void addPurchase(Purchase purchase){purchaseMapper.addPurchase(purchase);}
    //修改收货状态
    public void editReceiveStateByPurchaseNo(String purchase_no){purchaseMapper.editReceiveStateByPurchaseNo(purchase_no);}

}