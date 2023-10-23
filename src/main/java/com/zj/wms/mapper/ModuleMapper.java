package com.zj.wms.mapper;

import com.zj.wms.pojo.Module;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ModuleMapper {
    //查询权限分类
    @Select("select * from system_module;")
    List<Module> getAllModule();
}
