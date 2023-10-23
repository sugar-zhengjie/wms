package com.zj.wms.service;

import com.zj.wms.mapper.UnitMapper;
import com.zj.wms.pojo.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {
    @Autowired
    UnitMapper unitMapper;

    public List<Unit> getUnitsByKeywords(String keywords) {
        return unitMapper.getUnitsByKeywords(keywords);
    }

    public Integer getNewDisplayOrder() {
        Integer maxDisplayOrder = unitMapper.getMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addUnit(Unit unit) {
        unitMapper.addUnit(unit);
    }

    public Unit getByMultiUnitId(String multi_unit_id) {
        return unitMapper.getByMultiUnitId(multi_unit_id);
    }

    public void saveUnit(Unit unit) {
        unitMapper.saveUnit(unit);
    }

    public void deleteByMultiUnitId(String multi_unit_id) {
        unitMapper.deleteByUnitId(multi_unit_id);
    }
}
