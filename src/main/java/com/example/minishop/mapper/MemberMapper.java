package com.example.minishop.mapper;

import com.example.minishop.model.Member;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    @Select("select * from member where email = #{email} and password = #{password}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "zip", column = "zip"),
            @Result(property = "address1", column = "address1"),
            @Result(property = "address2", column = "address2"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "orders", column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.example.minishop.mapper.OrderMapper.findOrdersByMemberId",
                                 fetchType = FetchType.LAZY)),
            @Result(property = "carts", column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.example.minishop.mapper.CartMapper.findCartsByMemberId",
                                 fetchType = FetchType.LAZY))
    })
    Optional<Member> findMemberByEmailAndPassword(@Param("email") String phoneNumber, @Param("password") String password);

    @Select("select * from member where phone_number = #{phoneNumber} and password = #{password}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "zip", column = "zip"),
            @Result(property = "address1", column = "address1"),
            @Result(property = "address2", column = "address2"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "orders", column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.example.minishop.mapper.OrderMapper.findOrdersByMemberId",
                            fetchType = FetchType.LAZY)),
            @Result(property = "carts", column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.example.minishop.mapper.CartMapper.findCartsByMemberId",
                            fetchType = FetchType.LAZY))
    })
    Optional<Member> findMemberByPhoneNumberAndPassword(@Param("phoneNumber") String phoneNumber, @Param("password") String password);

    @Select("select count(*) from member where email = #{email}")
    int findMemberByEmail(@Param("email") String email);

    @Select("select count(*) from member where phone_number = #{phoneNumber}")
    int findMemberByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Insert("insert into member(id, name, email, password, phone_number, zip, address1, address2, created_at, updated_at) " +
            "values (#{id}, #{name}, #{email}, #{password}, #{phoneNumber}, #{zip}, #{address1}, #{address2}, #{createdAt}, #{updatedAt})")
    int insert(Member member);

    @Update("update member set " +
            "name = #{name}, " +
            "email = #{email}, " +
            "password = #{password}, " +
            "phone_number = #{phoneNumber}, " +
            "zip = #{zip}, " +
            "address1 = #{address1}, " +
            "address2 = #{address2}, " +
            "updated_at = #{updatedAt} " +
            "where id = #{id}")
    int update(Member member);
}
