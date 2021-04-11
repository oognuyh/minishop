package com.example.minishop.mapper;

import com.example.minishop.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GoodsMapper {

    @Select("select * from goods")
    List<Goods> findAllGoods();

    @Select("select * from goods where gcategory = #{gcategory}")
    List<Goods> findGoodsByGCategory(@Param("gcategory") String gCategory);

    @Select("select * from goods where gcode = #{gcode}")
    Optional<Goods> findGoodsByGCode(@Param("gcode") String gCode);
}