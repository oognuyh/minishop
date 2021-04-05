package com.example.minishop.dao;

import com.example.minishop.mapper.MemberMapper;
import com.example.minishop.model.Member;
import org.apache.ibatis.session.SqlSession;

public class MemberDao {

    public int insertMember(SqlSession session, Member member) {
        return session.getMapper(MemberMapper.class).insertMember(member);
    }

    public int countMembersById(SqlSession session, String userid) {
        return session.getMapper(MemberMapper.class).countMembersById(userid);
    }

    public int updateMember(SqlSession session, Member member) {
        return session.getMapper(MemberMapper.class).updateMember(member);
    }

    public Member findMemberByIdAndPasswd(SqlSession session, String userid, String passwd) {
        return session.getMapper(MemberMapper.class).findMemberByIdAndPasswd(userid, passwd);
    }
}
