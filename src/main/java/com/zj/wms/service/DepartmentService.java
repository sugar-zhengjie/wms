package com.zj.wms.service;

import com.zj.wms.mapper.DepartmentMapper;
import com.zj.wms.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getDepartmentsByKeywords(String keywords_no, String keywords_name) {
        return departmentMapper.getDepartmentsByKeywords(keywords_no, keywords_name);
    }

    public Integer getNewDisplayOrder() {
        Integer maxDisplayOrder = departmentMapper.selectMaxDisplayOrder();
        if (maxDisplayOrder == null) {
            maxDisplayOrder = 0;
        }
        return maxDisplayOrder + 1;
    }

    public void addDepartment(Department department) {
        departmentMapper.addDepartment(department);
    }

    public Department selectByDepartmentId(String department_id) {
        return departmentMapper.selectByDepartmentId(department_id);
    }

    public void saveDepartment(Department department) {
        departmentMapper.saveDepartment(department);
    }

    public void deleteByDepartmentId(String department_id) {
        departmentMapper.deleteByDepartmentId(department_id);
    }
}
