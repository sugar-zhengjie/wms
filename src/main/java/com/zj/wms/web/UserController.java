package com.zj.wms.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.wms.pojo.*;
import com.zj.wms.service.RoleService;
import com.zj.wms.service.UserService;

import com.zj.wms.service.WarehouseService;
import com.zj.wms.pojo.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService usi;
    @Autowired
    RoleService rs;
    @Autowired
    WarehouseService warehouseService;

    @RequestMapping({"login","/"})
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/login.action")
    public String login2(Model m, User u, HttpSession hs) {
        // 获得当前Subject
        Subject currentUser = SecurityUtils.getSubject();
        // 验证用户是否验证，即是否登录
        String msg = "";
        // 把用户名和密码封装为 UsernamePasswordToken 对象
        UsernamePasswordToken token = new UsernamePasswordToken(u.getAccount(), u.getPassword());
        // remembermMe记住密码,取决前端是否设置记住密码
        //token.setRememberMe(true);
        try {
            // 执行登录.
            currentUser.login(token);
            m.addAttribute("user",u);
            // 登录成功...
            User sessionUser=usi.selectUser(u.getAccount());
            hs.setAttribute("user", sessionUser);
            return "forward:/successLogin.action";
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误";
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定";
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用";
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期";
        } catch (UnknownAccountException e) {
            msg = "帐号不存在";
        } catch (UnauthorizedException e) {
                msg = "您没有得到相应的授权！";
        } catch (Exception e) {
            msg="账号或者密码错误";
        }
        m.addAttribute("error",msg);
        return "/login";
    }
    @RequestMapping("successLogin.action")
    public String successLogin(Model m,User u) {
        User user = usi.selectUser(u.getAccount());
        m.addAttribute("user", user);
        return "welcome";
    }
    @RequiresPermissions({"用户管理"})
    @RequestMapping("userList")
    public String userList(Model model,@RequestParam(value="stateCheck", defaultValue="") String stateCheck,@RequestParam(value="roleCheck", defaultValue="") String roleCheck,@RequestParam(value="name", defaultValue="") String name, @RequestParam(value="account", defaultValue="") String account, @RequestParam(value="start", defaultValue = "0") int start, @RequestParam(value="size", defaultValue = "5") int size){
        if(roleCheck.equals("请选择角色")){
            roleCheck="";
        }
        PageHelper.startPage(start, size, "display_order asc");
        List<UserList> users =usi.getUsersByCondition(stateCheck,roleCheck,account,name);
        PageInfo<UserList> page = new PageInfo<>(users);
        model.addAttribute("page", page);
        model.addAttribute("stateCheck", stateCheck);
        model.addAttribute("roleCheck", roleCheck);
        model.addAttribute("account", account);
        model.addAttribute("name", name);
        List<Role> roles=rs.getAllRole();
        model.addAttribute("roles",roles);
        return "userList";
    }
    @RequestMapping("passwordReset")
    public String passwordReset(String user_id,HttpSession hs){
        User user=usi.findUserByUserId(user_id);
        User u = (User) hs.getAttribute("user");
        user.setLast_updated_user_id(u.getUser_id());
        user.setLast_updated_user_name(u.getName());
        user.setLast_updated_date_time(new Date());
        //使用MD5加密
        ByteSource credentialsSalt01 = ByteSource.Util.bytes("wms");
        Object salt = null;//盐值
        Object credential = "123456";//密码---所有重置的密码都是123456
        String hashAlgorithmName = "MD5";//加密方式
        //1024指的是加密的次数
        Object simpleHash = new SimpleHash(hashAlgorithmName, credential, credentialsSalt01, 1024);
        //新的密码
        user.setPassword(simpleHash.toString());
        usi.saveUser(user);
        return "forward:userList";
    }
    @RequestMapping("editUserRole")
    public String editUserRole(Model model,String user_id){
        Role role=usi.findRoleByUserId(user_id);
        model.addAttribute("user_id",user_id);
        model.addAttribute("userRole",role);
        List<Role> roles=rs.getAllRole();
        model.addAttribute("roles",roles);
        return "editUserRole";
    }
    @RequestMapping("editUserRoleCommit")
    public String editUserRoleCommit(@RequestParam(value="user_id") String user_id,@RequestParam(value="userRole") String role_id){
        //用户和角色为一对一关系
        usi.updateUserRole(user_id,role_id);
        return "editSuccess";
    }
    @RequestMapping("editUser")
    public String editCustomer(Model m, String user_id){
        User user = usi.findUserByUserId(user_id);
        m.addAttribute("EditUser", user);
        return "editUser";
    }
    @RequestMapping("editUserCommit")
    public String editCustomerCommit(HttpSession hs, User user,@RequestParam(value="userRole") String role_id) {
        //此处添加从session中获取用户
        User u = (User) hs.getAttribute("user");
        user.setLast_updated_user_id(u.getUser_id());
        user.setLast_updated_user_name(u.getName());
        user.setLast_updated_date_time(new Date());
        usi.saveEditUser(user);
        usi.updateUserRole(user.getUser_id(),role_id);
        return "editSuccess";
    }
    @RequestMapping("addUser")
    public String addUser(Model model){
        List<Role> roles=rs.getAllRole();
        model.addAttribute("roles",roles);
        return "addUser";
    }
    @RequestMapping("addUserCommit")
    public String addUserCommit(HttpSession hs,User user,@RequestParam(value="roleCheck") String role_id){
        int display_order = usi.getNewDisplayOrder();
        String user_id = "user" + String.format("%05d", display_order);
        user.setDisplay_order(display_order);
        user.setUser_id(user_id);
        //其他必填字段--之后开发可用
        user.setTenant_id(user_id);
        user.setOrganization_units_id(user_id);
        user.setDepartment_id(user_id);
        user.setStation_id(user_id);
        user.setJob_id(user_id);
        user.setSex(1);
        user.setId_type(1);
        user.setState(1);
        user.setIs_deleted(1);
        //此处添加从session中获取用户
        User u = (User) hs.getAttribute("user");
        user.setCreated_user_id(u.getUser_id());
        user.setCreated_user_name(u.getName());
        user.setCreated_date_time(new Date());
        user.setLast_updated_user_id(u.getUser_id());
        user.setLast_updated_user_name(u.getName());
        user.setLast_updated_date_time(new Date());
        user.setAudit_state(1);
        user.setAudit_user_id(u.getUser_id());
        //加密
        ByteSource credentialsSalt01 = ByteSource.Util.bytes("wms");
        Object salt = null;//盐值
        Object credential =user.getPassword();//密码
        String hashAlgorithmName = "MD5";//加密方式
        Object simpleHash = new SimpleHash(hashAlgorithmName, credential, credentialsSalt01, 1024);
        user.setPassword(simpleHash.toString());
        usi.addUser(user);
        //为新增用户添加角色
        int display_order2=rs.getNewDisplayOrderFromUserRole();
        String user_role_id = "ur" + String.format("%03d", display_order2);
        UserRole userRole=new UserRole();
        userRole.setUser_role_id(user_role_id);
        userRole.setUser_id(user.getUser_id());
        userRole.setRole_id(role_id);
        userRole.setDisplay_order(display_order2);
        rs.addUserRole(userRole);
        return "addSuccess";
    }
    @RequestMapping("userStartAccount")
    public String userStartAccount(String[] user_ids){
        for(String s:user_ids){
            usi.startAccount(s);
        }
        return "forward:userList";
    }
    @RequestMapping("userStopAccount")
    public String userStopAccount(@RequestParam(value="user_ids") String[] user_ids){
       for(String s:user_ids){
           usi.stopAccount(s);
       }
        return "forward:userList";
    }
    @RequestMapping("editUserWarehouse")
    public String editUserWarehouse(Model model,String user_id){
        List<Warehouse> warehouses=warehouseService.selectWarehouseByUserId(user_id);
        model.addAttribute("warehouses",warehouses);
        model.addAttribute("user_id",user_id);
        return "editUserWarehouse";
    }
    @RequestMapping("deleteUserWarehouse")
    public String deleteUserWarehouse(@RequestParam(value="user_id") String user_id,@RequestParam(value="warehouse_id") String warehouse_id){
        warehouseService.deleteUserWarehouseByWarehouseId(warehouse_id);
        return "forward:editUserWarehouse";
    }
    @RequestMapping("addUserWarehouse")
    public String addUserWarehouse(Model model,String user_id){
        List<Warehouse> warehouses=warehouseService.selectNotWarehouseByUserId();
        model.addAttribute("warehouses",warehouses);
        model.addAttribute("user_id",user_id);
        return "addUserWarehouse";
    }
    @RequestMapping("addUserWarehouseCommit")
    public String addUserWarehouseCommit(@RequestParam(value="warehouse_ids") String[] warehouse_ids,@RequestParam(value="user_id") String user_id){
        for(String s:warehouse_ids){
            warehouseService.addUserWarehouseByWarehouseId(user_id,s);
        }
        return "editSuccess";
    }
    @RequestMapping("closedFunction")
    public String closedFunction(){
        return "closedFunction";
    }
}