package com.zj.wms.web;

import com.google.gson.Gson;
import com.zj.wms.pojo.Role;
import com.zj.wms.service.MenuService;
import com.zj.wms.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
@Controller
public class MenuController {
    @Autowired
    RoleService rs;
    @Autowired
    MenuService ms;
    @RequiresPermissions({"权限分配"})
    @RequestMapping("roles.action")
    public String rolesList(Model model){
        List<Role> roles=rs.getAllRole();
        model.addAttribute("roles",roles);
        return "permission";
    }
    //授权读取
    @RequestMapping("permissionList.action")
    @ResponseBody
    public String permissionList(String id){
        List<HashMap<String, Object>> result = ms.getPermissionList(id);
        Gson gson=new Gson();
        return gson.toJson(result);
    }
    //授权修改
    @ResponseBody
    @RequestMapping("savePermission")
    public boolean savePermission(String data,String id){
        return ms.savePermissionList(data,id);
    }
}
