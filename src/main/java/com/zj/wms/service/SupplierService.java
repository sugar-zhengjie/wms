package com.zj.wms.service;

import com.zj.wms.mapper.SupplierMapper;
import com.zj.wms.pojo.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    SupplierMapper supplierMapper;

    public List<Supplier> getSuppliersByKeywords(String keywords) {
        return supplierMapper.getSuppliersByKeywords(keywords);
    }

    public int getNewDisplayOrder() {
        Integer maxDisplayOrder = supplierMapper.getMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addSupplier(Supplier supplier) {
        supplierMapper.addSupplier(supplier);
    }

    public Supplier selectBySupplierId(String supplier_id) {
        return supplierMapper.selectBySupplierId(supplier_id);
    }

    public void saveSupplier(Supplier supplier) {
        supplierMapper.saveSupplier(supplier);
    }

    public void deleteBySupplierId(String supplier_id) {
        supplierMapper.deleteBySupplierId(supplier_id);
    }
}
