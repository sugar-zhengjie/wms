package com.zj.wms.service;

import com.zj.wms.mapper.MaterialMapper;
import com.zj.wms.mapper.MaterialTypeMapper;
import com.zj.wms.pojo.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    @Autowired
    MaterialMapper materialMapper;
    @Autowired
    MaterialTypeMapper materialTypeMapper;

    public List<Material> getMaterialsByKeywordsAndMaterialId(String material_type_id, String keywords_no, String keywords_name, String keywords_specifications) {
        if ("".equals(material_type_id)) {
            return materialMapper.getMaterialsByKeywords(keywords_no, keywords_name, keywords_specifications);
        }
        return materialMapper.getMaterialsByKeywordsAndMaterialId(material_type_id, keywords_no, keywords_name, keywords_specifications);
    }

    public Integer getNewDisplayOrder() {
        Integer maxDisplayOrder = materialMapper.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addMaterial(Material material) {
        String material_type_name = materialTypeMapper.selectByMaterialTypeId(material.getMaterial_type_id()).getName();
        material.setMaterial_type_name(material_type_name);
        materialMapper.addMaterial(material);
    }

    public Material selectByMaterialId(String material_id) {
        return materialMapper.selectByMaterialId(material_id);
    }

    public void saveMaterial(Material material) {
        String material_type_name = materialTypeMapper.selectByMaterialTypeId(material.getMaterial_type_id()).getName();
        material.setMaterial_type_name(material_type_name);
        materialMapper.saveMaterial(material);
    }

    public void deleteByMaterialId(String material_id) {
        materialMapper.deleteByMaterialId(material_id);
    }
    
}
