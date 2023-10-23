package com.zj.wms.service;

import com.zj.wms.mapper.LocationMapper;
import com.zj.wms.pojo.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    LocationMapper locationMapper;

    public int getNewDisplayOrder() {
        Integer maxDisplayOrder = locationMapper.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addLocation(Location location) {
        locationMapper.addLocation(location);
    }
    
    public Location selectByLocationId(String location_id) {
        return locationMapper.selectByLocationId(location_id);
    }

    public void saveLocation(Location location) {
        locationMapper.saveLocation(location);
    }

    public void deleteByLocationId(String location_id) {
        locationMapper.deleteByLocationId(location_id);
    }
}
