package com.zj.wms.service;



import com.zj.wms.mapper.UserMapper;
import com.zj.wms.pojo.Role;
import com.zj.wms.pojo.User;
import com.zj.wms.pojo.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UserMapper um;

    public User findUserByUserId(String user_id){ return um.findUserByUserId(user_id);};

    public User login(User u) {
        return um.login(u);
    }

    public User selectUser(String account) { return um.selectUser(account);}

    public List<UserList> getUsersByCondition(String stateCheck,String roleCheck,String account,String name){ return um.getUsersByCondition(stateCheck,roleCheck,account,name);}

    public void saveUser(User user){um.saveUser(user);}

    public Role findRoleByUserId(String user_id){return um.findRoleByUserId(user_id);}

    public void updateUserRole(String user_id,String role_id){um.updateUserRole(user_id,role_id);}

    public void saveEditUser(User user){um.saveEditUser(user);};

    public int getNewDisplayOrder() {
        Integer maxDisplayOrder = um.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }
    public void addUser(User user){ um.addUser(user);};

    public void stopAccount(String user_id){um.stopAccount(user_id);};
    public void startAccount(String user_id){um.startAccount(user_id);};
}

