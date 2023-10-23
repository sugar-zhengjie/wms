package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.User;
import com.zj.wms.pojo.Werks;
import com.zj.wms.service.WerksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class WerksController {
    @Autowired
    WerksService werksService;

    @RequestMapping("werksList")
    public String werksList(Model m, @RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size) {
        PageHelper.startPage(start, size, "display_order asc");
        List<Werks> werkses = werksService.getWerksesByKeywords(keywords);
        PageInfo<Werks> page = new PageInfo<>(werkses);
        m.addAttribute("page", page);
        m.addAttribute("keywords", keywords);
        return "werksList";
    }

    @RequestMapping("addWerks")
    public String addWerks(){
        return "addWerks";
    }

    @RequestMapping("addWerksCommit")
    public String addWerksCommit(HttpSession hs, Werks werks) {
        int display_order = werksService.getNewDisplayOrder();
        String werks_id = UUID.randomUUID().toString();
        werks.setWerks_id(werks_id);
        werks.setDisplay_order(display_order);
        //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
        User u = (User) hs.getAttribute("user");
        werks.setCreated_user_id(u.getUser_id());
        werks.setCreated_user_name(u.getName());
        werks.setCreated_date_time(new Date());
        werks.setLast_updated_user_id(u.getUser_id());
        werks.setLast_updated_user_name(u.getName());
        werks.setLast_updated_date_time(new Date());
        werksService.addWerks(werks);
        return "addSuccess";
    }

    @RequestMapping("editWerks")
    public String editWerks(Model m, String werks_id) {
        Werks werks = werksService.selectByWerksId(werks_id);
        m.addAttribute("onEditWerks", werks);
        return "editWerks";
    }

    @RequestMapping("editWerksCommit")
    public String editWerksCommit(HttpSession hs, Werks werks) {
        //从Session中获取用户, 嵌入最后更新人相关字段
        User u = (User) hs.getAttribute("user");
        werks.setLast_updated_user_id(u.getUser_id());
        werks.setLast_updated_user_name(u.getName());
        werks.setLast_updated_date_time(new Date());

        werksService.saveWerks(werks);
        return "editSuccess";
    }

    //soft delete
    @RequestMapping("deleteWerks")
    public String deleteWerks(String werks_id) {
        werksService.deleteByWerksId(werks_id);
        return "forward:werksList";
    }

}
