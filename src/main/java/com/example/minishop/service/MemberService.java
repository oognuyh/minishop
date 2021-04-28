package com.example.minishop.service;

import com.example.minishop.dao.MemberDao;
import com.example.minishop.factory.OracleSqlSessionFactory;
import com.example.minishop.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
public class MemberService {
    private final MemberDao memberDao = new MemberDao();

    public Optional<Member> signIn(String id, String password) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            boolean isPhoneNumber = id.chars().allMatch(Character::isDigit);

            return isPhoneNumber ?
                    memberDao.findMemberByPhoneNumberAndPassword(session, id, password) :
                    memberDao.findMemberByEmailAndPassword(session, id, password);
        }
    }

    public Optional<Member> signUp(Member member) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            Optional<Member> memberOptional = Optional.empty();

            try {
                if (memberDao.insert(session, member) != 1)
                    throw new IllegalArgumentException("Failed to sign up");

                session.commit();
                return signIn(member.getEmail(), member.getPassword());
            } catch (Exception e) {
                session.rollback();
                return memberOptional;
            }
        }
    }

    public boolean isDuplicateEmail(String email) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return memberDao.findMemberByEmail(session, email) == 1;
        }
    }

    public boolean isDuplicatePhoneNumber(String phoneNumber) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return memberDao.findMemberByPhoneNumber(session, phoneNumber) == 1;
        }
    }

    public boolean update(Member member) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            member.setUpdatedAt(LocalDateTime.now());
            memberDao.update(session, member);

            session.commit();
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }
}
