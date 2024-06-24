package com.nc13.spring_shop.service;

import com.nc13.spring_shop.model.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final SqlSession sqlSession;
    private final String NAMESPACE = "com.nc13.mappers.MemberMapper.";

    public MemberDTO auth(MemberDTO memberDTO) {
        return sqlSession.selectOne(NAMESPACE + "auth", memberDTO);
    }

    public boolean validateLoginId(String email) {
        return sqlSession.selectOne(NAMESPACE + "selectByEmail", email) == null;
    }

    public void insert(MemberDTO memberDTO) {
        sqlSession.insert(NAMESPACE + "register", memberDTO);
    }
}
