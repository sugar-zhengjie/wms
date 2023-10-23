package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.Department;
import com.zj.wms.pojo.User;
import com.zj.wms.service.DepartmentService;
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
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("departmentList")
    public String departmentList(Model m, @RequestParam(value="keywords_no", defaultValue="") String keywords_no, @RequestParam(value="keywords_name", defaultValue="") String keywords_name , @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size){
        PageHelper.startPage(start, size, "display_order asc");
        List<Department> departments = departmentService.getDepartmentsByKeywords(keywords_no, keywords_name);
        PageInfo<Department> page = new PageInfo<>(departments);
        m.addAttribute("page", page);
        m.addAttribute("keywords_no", keywords_no);
        m.addAttribute("keywords_name", keywords_name);
        return "departmentList";
    }

    @RequestMapping("addDepartment")
    public String addDepartment(){
        return "addDepartment";
    }

    @RequestMapping("addDepartmentCommit")
    public String addDepartmentCommit(HttpSession hs, Department department) {
        int display_order = departmentService.getNewDisplayOrder();
        String department_id = UUID.randomUUID().toString();
        department.setDepartment_id(department_id);
        department.setDisplay_order(display_order);
        //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
        User u = (User) hs.getAttribute("user");
        department.setCreated_user_id(u.getUser_id());
        department.setCreated_user_name(u.getName());
        department.setCreated_date_time(new Date());
        department.setLast_updated_user_id(u.getUser_id());
        department.setLast_updated_user_name(u.getName());
        department.setLast_updated_date_time(new Date());
        departmentService.addDepartment(department);
        return "addSuccess";
    }

    @RequestMapping("editDepartment")
    public String editDepartment(Model m, String department_id) {
        Department department = departmentService.selectByDepartmentId(department_id);
        m.addAttribute("onEditDepartment", department);
        return "editDepartment";
    }

    @RequestMapping("editDepartmentCommit")
    public String editDepartmentCommit(HttpSession hs, Department department) {
        //从Session中获取用户, 嵌入最后更新人相关字段
        User u = (User) hs.getAttribute("user");
        department.setLast_updated_user_id(u.getUser_id());
        department.setLast_updated_user_name(u.getName());
        department.setLast_updated_date_time(new Date());

        departmentService.saveDepartment(department);
        return "editSuccess";
    }

    //soft delete
    @RequestMapping("deleteDepartment")
    public String deleteDepartment(String department_id) {
        departmentService.deleteByDepartmentId(department_id);
        return "forward:departmentList";
    }


}
