package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.Role;
import com.zj.wms.pojo.User;
import com.zj.wms.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
@Controller
public class RoleController {
    @Autowired
    RoleService rs;

    @RequiresPermissions({"角色管理"})
    @RequestMapping("roleList")
    public String roleList(Model m, @RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "10") int size){
        PageHelper.startPage(start, size, "display_order asc");
        List<Role> roles=rs.getRolesByKeywords(keywords);
        PageInfo<Role> page=new PageInfo<>(roles);
        m.addAttribute("page",page);
        m.addAttribute("keywords",keywords);
        return "roleList";
    }
    @RequestMapping("editRole")
    public String editCustomer(Model m, String role_id){
        Role role=rs.getRoleByRoleId(role_id);
        m.addAttribute("onEditRole", role);
        return "editRole";
    }
    @RequestMapping("editRoleCommit")
    public String editCustomerCommit(HttpSession hs, Role role) {
        //此处添加从session中获取用户
        User u = (User) hs.getAttribute("user");
        role.setLast_updated_user_id(u.getUser_id());
        role.setLast_updated_user_name(u.getName());
        role.setLast_updated_date_time(new Date());
        rs.saveEditRole(role);
        return "editSuccess";
    }

    @RequestMapping("deleteRole")
    public String deleteRole(String role_id) {
        rs.deleteByRoleId(role_id);
        return "forward:roleList";
    }

    @RequestMapping("addRole")
    public String  addRole() {
        return "addRole";
    }

    @RequestMapping("addRoleCommit")
    public String  addCustomerCommit(HttpSession hs, Role role) {
        int display_order = rs.getNewDisplayOrder();
        String role_id = "r" + String.format("%03d", display_order);
        role.setDisplay_order(display_order);
        role.setRole_id(role_id);
        //此处添加从session中获取用户, 设置customer的创建人id和name字段以及创建时间字段, 这里仅用admin账户示例
        User u = (User) hs.getAttribute("user");
        role.setCreated_user_id(u.getUser_id());
        role.setCreated_user_name(u.getName());
        role.setCreated_date_time(new Date());
        role.setLast_updated_user_id(u.getUser_id());
        role.setLast_updated_user_name(u.getName());
        role.setLast_updated_date_time(new Date());
        role.setAudit_user_id(u.getUser_id());
        rs.addRole(role);
        return "addSuccess";
    }

}
