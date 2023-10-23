package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.Material;
import com.zj.wms.pojo.User;
import com.zj.wms.service.MaterialService;
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
public class MaterialController {
    @Autowired
    MaterialService materialService;

    @RequestMapping("materialList")
    public String materialList(Model m, @RequestParam(value="material_type_id", defaultValue="") String material_type_id, @RequestParam(value="keywords_no", defaultValue="") String keywords_no, @RequestParam(value="keywords_name", defaultValue="") String keywords_name , @RequestParam(value="keywords_specifications", defaultValue="") String keywords_specifications, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size){
        PageHelper.startPage(start, size, "display_order asc");
        List<Material> materials = materialService.getMaterialsByKeywordsAndMaterialId(material_type_id, keywords_no, keywords_name, keywords_specifications);
        PageInfo<Material> page = new PageInfo<>(materials);
        m.addAttribute("page", page);
        m.addAttribute("material_type_id", material_type_id);
        m.addAttribute("keywords_no", keywords_no);
        m.addAttribute("keywords_name", keywords_name);
        m.addAttribute("keywords_specifications", keywords_specifications);
        return "materialList";
    }

    @RequestMapping("addMaterial")
    public String addMaterial(){
        return "addMaterial";
    }

    @RequestMapping("addMaterialCommit")
    public String addMaterialCommit(HttpSession hs, Material material) {
        int display_order = materialService.getNewDisplayOrder();
        String material_id = UUID.randomUUID().toString();
        material.setMaterial_id(material_id);
        material.setDisplay_order(display_order);
        //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
        User u = (User) hs.getAttribute("user");
        material.setCreated_user_id(u.getUser_id());
        material.setCreated_user_name(u.getName());
        material.setCreated_date_time(new Date());
        material.setLast_updated_user_id(u.getUser_id());
        material.setLast_updated_user_name(u.getName());
        material.setLast_updated_date_time(new Date());
        materialService.addMaterial(material);
        return "addSuccess";
    }

    @RequestMapping("editMaterial")
    public String editMaterial(Model m, String material_id) {
        Material material = materialService.selectByMaterialId(material_id);
        m.addAttribute("onEditMaterial", material);
        return "editMaterial";
    }

    @RequestMapping("editMaterialCommit")
    public String editMaterialCommit(HttpSession hs, Material material) {
        //从Session中获取用户, 嵌入最后更新人相关字段
        User u = (User) hs.getAttribute("user");
        material.setLast_updated_user_id(u.getUser_id());
        material.setLast_updated_user_name(u.getName());
        material.setLast_updated_date_time(new Date());

        materialService.saveMaterial(material);
        return "editSuccess";
    }

    //soft delete
    @RequestMapping("deleteMaterial")
    public String deleteMaterial(String material_id) {
        materialService.deleteByMaterialId(material_id);
        return "forward:materialList";
    }
}
