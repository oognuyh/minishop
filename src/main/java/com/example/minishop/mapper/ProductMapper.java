package com.example.minishop.mapper;

import com.example.minishop.model.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select * from product where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "description", column = "description"),
            @Result(property = "image", column = "image"),
            @Result(property = "categoryName", column = "category_id",
                    javaType = String.class,
                    one = @One(select = "com.example.minishop.mapper.CategoryMapper.findCategoryNameById", fetchType = FetchType.EAGER))
    })
    Product findProductById(@Param("id") int id);

    @Select("select * from product where category_id = #{categoryId}")
    List<Product> findProductsByCategoryId(@Param("categoryId") int categoryId);
}
