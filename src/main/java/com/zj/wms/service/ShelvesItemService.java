package com.zj.wms.service;

import com.zj.wms.mapper.ShelvesItemMapper;
import com.zj.wms.pojo.ShelvesItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelvesItemService {
    @Autowired
    ShelvesItemMapper shelvesItemMapper;

    public Integer getNewDisplayOrder() {
        Integer maxDisplayOrder = shelvesItemMapper.getMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addShelvesItem(ShelvesItem shelves_item) {
        shelvesItemMapper.addShelvesItem(shelves_item);
    }
}
