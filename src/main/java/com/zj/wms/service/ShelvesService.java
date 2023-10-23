package com.zj.wms.service;

import com.zj.wms.mapper.ShelvesMapper;
import com.zj.wms.pojo.Shelves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelvesService {
    @Autowired
    ShelvesMapper shelvesMapper;

    public Integer getNewDisplayOrder() {
        Integer maxDisplayOrder = shelvesMapper.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addShelves(Shelves shelves) {
        shelvesMapper.addShelves(shelves);
    }
}
