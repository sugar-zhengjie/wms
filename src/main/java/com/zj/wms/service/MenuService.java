package com.zj.wms.service;

import com.zj.wms.mapper.MenuMapper;
import com.zj.wms.mapper.ModuleMapper;
import com.zj.wms.mapper.RoleMapper;
import com.zj.wms.pojo.Menu;
import com.zj.wms.pojo.Module;
import com.zj.wms.pojo.RoleMenu;
import com.zj.wms.utils.MenuTree;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class MenuService {

    @Autowired
    MenuMapper mm;
    @Autowired
    RoleMapper rm;
    @Autowired
    ModuleMapper modp;

    public List<Menu> getPermissionByAccount(String account) {
        return mm.getPermissionByAccount(account);
    }

    public List<Menu> getAllMenu() { return mm.getAllMenu(); }

    public List<Module> getAllModule() { return modp.getAllModule(); }

    public List<Menu> getMenuByRoleId(String role_id) { return rm.getMenuByRoleId(role_id); }

    public List<Menu> getMenuByParentMenuId(String parent_menu_id) { return mm.getMenuByParentMenuId(parent_menu_id); }

    public void deleteRoleMenu(String role_id) { mm.deleteRoleMenu(role_id); }

    public String selectMenuIdByName(String name) { return mm.selectMenuIdByName(name); }

    //获得最大role-menu中间表id
    public int getNewRoleMenuId(){
        Integer maxNewRoleMenuId=mm.selectMaxDisplayOrder();
        if(maxNewRoleMenuId==null){
            maxNewRoleMenuId=0;
        }
        return maxNewRoleMenuId+1;
    }
    //保存更新的角色权限
    public void saveRoleMenu(RoleMenu roleMenu){
         mm.saveRoleMenu(roleMenu);
    };

    //角色对应权限列表
    public List<HashMap<String, Object>> getPermissionList(String id){
        //多层树用递归
        List<HashMap<String, Object>> result = new ArrayList<>();//定义一个map处理json键名问题
        //查询角色所拥有权限
        List<Menu> roleMenu=rm.getMenuByRoleId(id);
        //查询所有菜单
        List<Module> modules=modp.getAllModule();
        for(Module module:modules){
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("id",TreeId(module.getModule_id()));
            map1.put("title",module.getName());
            map1.put("spread", true);//设置是否展开
            //查询该菜单所有自菜单
            List<Menu> module_children=mm.getMenuByParentMenuId(module.getModule_id());
            List<HashMap<String, Object>> result2 = new ArrayList<>();
            for(Menu menu:module_children){
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("id",1000+TreeId(menu.getMenu_id()));
                map2.put("title",menu.getName());
                map2.put("spread", true);//设置是否展开
                for(Menu m:roleMenu){
                    if(m.getName().equals(menu.getName())){
                        map2.put("checked", true);    //设置为选中状态
                    }
                }
                result2.add(map2);
            }
            map1.put("children",result2);
            result.add(map1);
        }
        return result;
    }
    //处理layui树形插件数据中的id
    public int TreeId(String  s){
        return Integer.parseInt(s.substring(s.length()-2));
    }
    //保存新的授权
    public boolean savePermissionList(String data,String id){
        ObjectMapper mapper=new ObjectMapper();
        //data为字符串json数据
        try {
            List<MenuTree> list = mapper.readValue(data,new TypeReference<List<MenuTree>>(){});
            //根据ID删除该角色所有权限
            mm.deleteRoleMenu(id);
            return mt(list, id);
        } catch (Exception e) {
            return false;
        }
    }
    //递归保存
    private Boolean mt(List<MenuTree> list, String id) {
        try {
            for(MenuTree m: list){
                for(MenuTree mt:m.getChildren()){
                    int display_order=getNewRoleMenuId();
                    RoleMenu rm=new RoleMenu();
                    rm.setMenu_id(mm.selectMenuIdByName(mt.getTitle()));
                    rm.setRole_id(id);
                    rm.setDisplay_order(display_order);
                    rm.setRole_menu_id("rm"+String.format("%04d",display_order));
                    mm.saveRoleMenu(rm);
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
