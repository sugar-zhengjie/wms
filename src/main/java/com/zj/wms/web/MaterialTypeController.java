package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.MaterialType;
import com.zj.wms.pojo.User;
import com.zj.wms.service.MaterialTypeService;
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
public class MaterialTypeController {
    @Autowired
    MaterialTypeService materialTypeService;

    @RequestMapping("materialTypeList")
    public String materialTypeList(Model m, @RequestParam(value="keywords", defaultValue="") String keywords, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size){
        PageHelper.startPage(start, size, "display_order asc");
        List<MaterialType> materialTypes = materialTypeService.getMaterialTypesByKeywords(keywords);
        PageInfo<MaterialType> page = new PageInfo<>(materialTypes);
        m.addAttribute("page", page);
        m.addAttribute("keywords", keywords);
        return "materialTypeList";
    }

    @RequestMapping("addMaterialType")
    public String addMaterialType(){
        return "addMaterialType";
    }

    @RequestMapping("addMaterialTypeCommit")
    public String addMaterialTypeCommit(HttpSession hs, MaterialType materialType) {
        int display_order = materialTypeService.getNewDisplayOrder();
        String material_type_id = UUID.randomUUID().toString();
        materialType.setMaterial_type_id(material_type_id);
        materialType.setDisplay_order(display_order);
        //从session中获取用户, 补全创建人id姓名时间字段以及最后修改人相关字段
        User u = (User) hs.getAttribute("user");
        materialType.setCreated_user_id(u.getUser_id());
        materialType.setCreated_user_name(u.getName());
        materialType.setCreated_date_time(new Date());
        materialType.setLast_updated_user_id(u.getUser_id());
        materialType.setLast_updated_user_name(u.getName());
        materialType.setLast_updated_date_time(new Date());
        materialTypeService.addMaterialType(materialType);
        return "addSuccess";
    }

    @RequestMapping("editMaterialType")
    public String editMaterialType(Model m, String material_type_id) {
        MaterialType materialType = materialTypeService.selectByMaterialTypeId(material_type_id);
        m.addAttribute("onEditMaterialType", materialType);
        return "editMaterialType";
    }

    @RequestMapping("editMaterialTypeCommit")
    public String editMaterialTypeCommit(HttpSession hs, MaterialType materialType) {
        //从Session中获取用户, 嵌入最后更新人相关字段
        User u = (User) hs.getAttribute("user");
        materialType.setLast_updated_user_id(u.getUser_id());
        materialType.setLast_updated_user_name(u.getName());
        materialType.setLast_updated_date_time(new Date());

        materialTypeService.saveMaterialType(materialType);
        return "editSuccess";
    }

    //soft delete
    @RequestMapping("deleteMaterialType")
    public String deleteMaterialType(String material_type_id) {
        materialTypeService.deleteByMaterialTypeId(material_type_id);
        return "forward:materialTypeList";
    }

    @RequestMapping("getMaterialTypes")
    @ResponseBody
    public List<MaterialType> getMaterialTypes() {
        return materialTypeService.getMaterialTypesByKeywords("");
    }

}
