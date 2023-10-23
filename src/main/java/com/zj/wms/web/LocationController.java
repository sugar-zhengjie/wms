package com.zj.wms.web;

import com.zj.wms.pojo.Location;
import com.zj.wms.pojo.LocationTreeItem;
import com.zj.wms.pojo.User;
import com.zj.wms.service.LocationService;
import com.zj.wms.service.WerksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class LocationController {
    @Autowired
    LocationService locationService;
    @Autowired
    WerksService werksService;

    @RequestMapping("locationTree")
    public String locationTree(){
        return "locationTree";
    }

    @RequestMapping("getLocationsOnTree")
    @ResponseBody
    public List<LocationTreeItem> getLocationsOnTree() {
        List<LocationTreeItem> treeData = werksService.getWerksForTree();
        return treeData;
    }

    @RequestMapping("addLocation")
    public String addLocation(String lgort, Model m) {
        m.addAttribute("lgort", lgort);
        return "addLocation";
    }

    @RequestMapping("addLocationCommit")
    public String addLocationCommit(HttpSession hs, Location location) {
        location.setLocation_id(UUID.randomUUID().toString());
        int display_order = locationService.getNewDisplayOrder();
        location.setDisplay_order(display_order);
        //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
        User u = (User) hs.getAttribute("user");
        location.setCreated_user_id(u.getUser_id());
        location.setCreated_user_name(u.getName());
        location.setCreated_date_time(new Date());
        location.setLast_updated_user_id(u.getUser_id());
        location.setLast_updated_user_name(u.getName());
        location.setLast_updated_date_time(new Date());
        locationService.addLocation(location);
        return "addSuccess";
    }

    @RequestMapping("editLocation")
    public String editLocation(Model m, String location_id) {
        Location location = locationService.selectByLocationId(location_id);
        m.addAttribute("onEditLocation", location);
        return "editLocation";
    }

    @RequestMapping("editLocationCommit")
    public String editLocationCommit(HttpSession hs, Location location) {
        //从Session中获取用户, 嵌入最后更新人相关字段
        User u = (User) hs.getAttribute("user");
        location.setLast_updated_user_id(u.getUser_id());
        location.setLast_updated_user_name(u.getName());
        location.setLast_updated_date_time(new Date());

        locationService.saveLocation(location);
        return "editSuccess";
    }

    //soft delete
    @RequestMapping("deleteLocation")
    public String deleteLocation(String location_id) {
        locationService.deleteByLocationId(location_id);
        return "forward:locationTree";
    }

    @RequestMapping("chooseLocation")
    public String chooseLocation(String purchase_apply_item_id, Model m){
        m.addAttribute("purchase_apply_item_id", purchase_apply_item_id);
        return "chooseLocation";
    }

}
