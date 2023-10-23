package com.zj.wms.service;

import com.zj.wms.mapper.WarehouseMapper;
import com.zj.wms.pojo.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    WarehouseMapper warehouseMapper;


    public void addWarehouse(Warehouse warehouse) {
        warehouseMapper.addWarehouse(warehouse);
    }

    public int getNewDisplayOrder() {
        Integer maxDisplayOrder = warehouseMapper.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public Warehouse selectByWarehouseId(String warehouse_id) {
        return warehouseMapper.selectByWarehouseId(warehouse_id);
    }

    public void saveWarehouse(Warehouse warehouse) {
        warehouseMapper.saveWarehouse(warehouse);
    }

    public void deleteByWarehouseId(String warehouse_id) {
        warehouseMapper.deleteByWarehouseId(warehouse_id);
    }

    public List<Warehouse> selectWarehouseByUserId(String user_id){return warehouseMapper.selectWarehouseByUserId(user_id);};

    public List<Warehouse> selectNotWarehouseByUserId(){return warehouseMapper.selectNotWarehouseByUserId();};

    public void deleteUserWarehouseByWarehouseId(String warehouse_id){ warehouseMapper.deleteUserWarehouseByWarehouseId(warehouse_id);};

    public void addUserWarehouseByWarehouseId(String user_id,String warehouse_id){ warehouseMapper.addUserWarehouseByWarehouseId(user_id,warehouse_id);}

}
