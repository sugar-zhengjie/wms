package com.zj.wms.mapper;

import com.zj.wms.pojo.LocationTreeItem;
import com.zj.wms.pojo.Werks;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WerksMapper {

    @Select("select werks_id,werks_no,name,remark from werks where is_deleted=0 and name like '%${keywords}%'")
    List<Werks> getWerksesByKeywords(String keywords);

    @Select("select max(display_order) from werks")
    Integer selectMaxDisplayOrder();

    @Insert("insert into werks(werks_id,werks_no,name,remark,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,display_order) values(#{werks_id},#{werks_no},#{name},#{remark},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{display_order})")
    void addWerks(Werks werks);

    @Select("select werks_id,werks_no,name,remark from werks where is_deleted=0 and werks_id=#{werks_id}")
    Werks selectByWerksId(String werks_id);

    @Update("update werks set werks_no=#{werks_no},name=#{name},remark=#{remark},last_updated_user_id=#{last_updated_user_id},last_updated_user_name=#{last_updated_user_name},last_updated_date_time=#{last_updated_date_time} where werks_id=#{werks_id}")
    void saveWerks(Werks werks);

    @Update("update werks set is_deleted=1 where werks_id=#{werks_id}")
    void deleteByWerksId(String werks_id);

    @Select("select name,werks_id,werks_no from werks where is_deleted=0")
    @Results({
            @Result(property = "id", column = "werks_id"),
            @Result(property = "no", column = "werks_no"),
            @Result(property = "title", column = "name"),
            @Result(property = "children", javaType = List.class, column = "werks_id",
                many = @Many(select = "com.zj.wms.mapper.WarehouseMapper.getByWerksIdForTree")
            )
    })
    List<LocationTreeItem> getWerksForTree();
}
