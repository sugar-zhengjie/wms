package com.zj.wms.service;

import com.zj.wms.mapper.WerksMapper;
import com.zj.wms.pojo.LocationTreeItem;
import com.zj.wms.pojo.Werks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WerksService {
    @Autowired
    WerksMapper werksMapper;

    public List<Werks> getWerksesByKeywords(String keywords) {
        return werksMapper.getWerksesByKeywords(keywords);
    }

    public Integer getNewDisplayOrder() {
        Integer maxDisplayOrder = werksMapper.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addWerks(Werks werks) {
        werksMapper.addWerks(werks);
    }

    public Werks selectByWerksId(String werks_id) {
        return werksMapper.selectByWerksId(werks_id);
    }

    public void saveWerks(Werks werks) {
        werksMapper.saveWerks(werks);
    }

    public void deleteByWerksId(String werks_id) {
        werksMapper.deleteByWerksId(werks_id);
    }

    public List<LocationTreeItem> getWerksForTree() {
        List<LocationTreeItem> werkses = werksMapper.getWerksForTree();
        for (LocationTreeItem werks : werkses) {
            werks.setLay("1");
            for (LocationTreeItem warehouse : werks.getChildren()) {
                warehouse.setLay("2");
                for (LocationTreeItem location : warehouse.getChildren()) {
                    location.setLay("3");
                }
            }
        }
        return werkses;
    }
}
