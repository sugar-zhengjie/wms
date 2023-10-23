package com.zj.wms.mapper;

import com.zj.wms.pojo.Unit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UnitMapper {

    @Select("select multi_unit_id,multi_unit_no,unit,remark from unit where is_deleted=0 and unit like '%${keywords}%'")
    List<Unit> getUnitsByKeywords(String keywords);

    @Select("select max(display_order) from unit")
    Integer getMaxDisplayOrder();

    @Insert("insert into unit(multi_unit_id,multi_unit_no,unit,remark,created_user_id,created_user_name,created_date_time,last_updated_user_id,last_updated_user_name,last_updated_date_time,display_order) values(#{multi_unit_id},#{multi_unit_no},#{unit},#{remark},#{created_user_id},#{created_user_name},#{created_date_time},#{last_updated_user_id},#{last_updated_user_name},#{last_updated_date_time},#{display_order})")
    void addUnit(Unit unit);

    @Select("select multi_unit_id,multi_unit_no,unit,remark from unit where is_deleted=0 and multi_unit_id=#{multi_unit_id}")
    Unit getByMultiUnitId(String multi_unit_id);

    @Update("update unit set multi_unit_no=#{multi_unit_no},unit=#{unit},remark=#{remark},last_updated_user_id=#{last_updated_user_id},last_updated_user_name=#{last_updated_user_name},last_updated_date_time=#{last_updated_date_time} where multi_unit_id=#{multi_unit_id}")
    void saveUnit(Unit unit);

    @Update("update unit set is_deleted=1 where multi_unit_id=#{multi_unit_id}")
    void deleteByUnitId(String multi_unit_id);

}
