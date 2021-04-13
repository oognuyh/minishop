package com.example.minishop.service;

import com.example.minishop.dao.MemberDao;
import com.example.minishop.factory.OracleSqlSessionFactory;
import com.example.minishop.model.Member;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class MemberService {
    MemberDao memberDao = new MemberDao();

    public boolean signUp(Member member) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            if (memberDao.insertMember(session, member) == 1) {
                session.commit();
                return true;
            } else {
                session.rollback();
                return false;
            }
        }
    }

    public Optional<Member> signIn(String userid, String passwd) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return memberDao.findMemberByIdAndPasswd(session, userid, passwd);
        }
    }

    public boolean isDuplicateId(String userid) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return memberDao.countMembersById(session, userid) == 1;
        }
    }

    public boolean updateMember(Member member) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            if (memberDao.updateMember(session, member) == 1) {
                session.commit();
                return true;
            } else {
                session.rollback();
                return false;
            }
        }
    }
}
