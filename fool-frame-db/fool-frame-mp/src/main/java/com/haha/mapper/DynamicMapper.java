package com.haha.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DynamicMapper {


    @Select("#{sql}")
    List selectList(@Param("sql") String sql);

    @Delete("#{sql}")
    int delete(@Param("sql") String sql);

    @Update("#{sql}")
    int update(@Param("sql") String sql);

    @Insert("#{sql}")
    int insert(@Param("sql") String sql);

}
