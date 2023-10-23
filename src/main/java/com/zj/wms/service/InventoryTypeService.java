package com.zj.wms.service;

import com.zj.wms.mapper.InventoryTypeMapper;
import com.zj.wms.pojo.InventoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryTypeService {
    @Autowired
    InventoryTypeMapper inventoryTypeMapper;

    public List<InventoryType> getInventoryTypesByKeywords(String keywords) {
        return inventoryTypeMapper.getInventoryTypesByKeywords(keywords);
    }

    public Integer getNewDisplayOrder() {
        Integer maxDisplayOrder = inventoryTypeMapper.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addInventoryType(InventoryType inventoryType) {
        inventoryTypeMapper.addInventoryType(inventoryType);
    }

    public InventoryType selectByInventoryTypeId(String inventoryType_id) {
        return inventoryTypeMapper.selectByInventoryTypeId(inventoryType_id);
    }

    public void saveInventoryType(InventoryType inventoryType) {
        inventoryTypeMapper.saveInventoryType(inventoryType);
    }

    public void deleteByInventoryTypeId(String inventoryType_id) {
        inventoryTypeMapper.deleteByInventoryTypeId(inventoryType_id);
    }

}
