package com.zj.wms.service;

import com.zj.wms.mapper.MaterialTypeMapper;
import com.zj.wms.pojo.MaterialType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialTypeService {
    @Autowired
    MaterialTypeMapper materialTypeMapper;

    public List<MaterialType> getMaterialTypesByKeywords(String keywords) {
        return materialTypeMapper.getMaterialTypesByKeywords(keywords);
    }

    public Integer getNewDisplayOrder() {
        Integer maxDisplayOrder = materialTypeMapper.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addMaterialType(MaterialType materialType) {
        materialTypeMapper.addMaterialType(materialType);
    }

    public MaterialType selectByMaterialTypeId(String materialType_id) {
        return materialTypeMapper.selectByMaterialTypeId(materialType_id);
    }

    public void saveMaterialType(MaterialType materialType) {
        materialTypeMapper.saveMaterialType(materialType);
    }

    public void deleteByMaterialTypeId(String materialType_id) {
        materialTypeMapper.deleteByMaterialTypeId(materialType_id);
    }
}
