package com.example.minishop.dao;

import com.example.minishop.mapper.MemberMapper;
import com.example.minishop.model.Member;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class MemberDao {

    public Optional<Member> findMemberByEmailAndPassword(SqlSession session, String email, String password) {
        return session.getMapper(MemberMapper.class).findMemberByEmailAndPassword(email, password);
    }

    public Optional<Member> findMemberByPhoneNumberAndPassword(SqlSession session, String phoneNumber, String password) {
        return session.getMapper(MemberMapper.class).findMemberByPhoneNumberAndPassword(phoneNumber, password);
    }

    public int findMemberByEmail(SqlSession session, String email) {
        return session.getMapper(MemberMapper.class).findMemberByEmail(email);
    }

    public int findMemberByPhoneNumber(SqlSession session, String phoneNumber) {
        return session.getMapper(MemberMapper.class).findMemberByPhoneNumber(phoneNumber);
    }

    public int insert(SqlSession session, Member member) {
        return session.getMapper(MemberMapper.class).insert(member);
    }

    public int update(SqlSession session, Member member) {
        return session.getMapper(MemberMapper.class).update(member);
    }
}
