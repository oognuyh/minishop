package com.example.minishop.mapper;

import com.example.minishop.model.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    @Select("select * from member")
    List<Member> findAllMembers();

    @Select("select count(*) from member where userid = #{userid}")
    int countMembersById(String userid);

    @Select("select * from member where userid = #{userid} and passwd = #{passwd}")
    Optional<Member> findMemberByIdAndPasswd(@Param("userid") String userid, @Param("passwd") String passwd);

    @Insert("insert into member values (#{userid}, #{passwd}, #{username}, #{post}, #{addr1}, #{addr2}, #{phone1}, #{phone2}, #{phone3}, #{email1}, #{email2})")
    int insertMember(Member member);

    @Update("update member " +
            "set " +
            "username = #{username}, " +
            "passwd = #{passwd}, " +
            "post = #{post}, " +
            "addr1 = #{addr1}, " +
            "addr2 = #{addr2}, " +
            "phone1 = #{phone1}, " +
            "phone2 = #{phone2}, " +
            "phone3 = #{phone3}, " +
            "email1 = #{email1}, " +
            "email2 = #{email2} " +
            "where userid = #{userid}")
    int updateMember(Member member);
}
