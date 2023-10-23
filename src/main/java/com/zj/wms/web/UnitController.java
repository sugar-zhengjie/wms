package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.Unit;
import com.zj.wms.pojo.User;
import com.zj.wms.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class UnitController {
    @Autowired
    UnitService unitService;

    @RequestMapping("unitList")
    public String unitList(Model m, @RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size) {
        PageHelper.startPage(start, size, "display_order asc");
        List<Unit> units = unitService.getUnitsByKeywords(keywords);
        PageInfo<Unit> page = new PageInfo<>(units);
        m.addAttribute("page", page);
        m.addAttribute("keywords", keywords);
        return "unitList";
    }

    @RequestMapping("addUnit")
    public String addUnit(){
        return "addUnit";
    }

    @RequestMapping("addUnitCommit")
    public String addUnitCommit(HttpSession hs, Unit unit) {
        int display_order = unitService.getNewDisplayOrder();
        String multi_unit_id = UUID.randomUUID().toString();
        unit.setMulti_unit_id(multi_unit_id);
        unit.setDisplay_order(display_order);
        //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
        User u = (User) hs.getAttribute("user");
        unit.setCreated_user_id(u.getUser_id());
        unit.setCreated_user_name(u.getName());
        unit.setCreated_date_time(new Date());
        unit.setLast_updated_user_id(u.getUser_id());
        unit.setLast_updated_user_name(u.getName());
        unit.setLast_updated_date_time(new Date());

        unitService.addUnit(unit);
        return "addSuccess";
    }

    @RequestMapping("editUnit")
    public String editUnit(Model m, String multi_unit_id) {
        Unit unit = unitService.getByMultiUnitId(multi_unit_id);
        m.addAttribute("onEditUnit", unit);
        return "editUnit";
    }

    @RequestMapping("editUnitCommit")
    public String editUnitCommit(HttpSession hs, Unit unit) {
        //从Session中获取用户, 嵌入最后更新人相关字段
        User u = (User) hs.getAttribute("user");
        unit.setLast_updated_user_id(u.getUser_id());
        unit.setLast_updated_user_name(u.getName());
        unit.setLast_updated_date_time(new Date());

        unitService.saveUnit(unit);
        return "editSuccess";
    }

    //soft delete
    @RequestMapping("deleteUnit")
    public String deleteUnit(String multi_unit_id){
        unitService.deleteByMultiUnitId(multi_unit_id);
        return "forward:unitList";
    }

    @RequestMapping("getUnits")
    @ResponseBody
    public List<Unit> getUnits() {
        return unitService.getUnitsByKeywords("");
    }

}
