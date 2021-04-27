package com.example.minishop.mapper;

import com.example.minishop.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from category")
    List<Category> findAllCategories();

    @Select("select name from category where id = #{id}")
    String findCategoryNameById(@Param("id") int id);
}
