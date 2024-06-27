package com.nc13.spring_shop.service;

import com.nc13.spring_shop.model.CartDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final SqlSession sqlSession;
    private final String NAMESPACE = "com.nc13.mappers.CartMapper.";

    public void insert(CartDTO cartDTO) {
        sqlSession.insert(NAMESPACE + "insert", cartDTO);
    }

    public List<CartDTO> selectAll(int memberId) {
        return sqlSession.selectList(NAMESPACE + "selectAll", memberId);
    }

    public CartDTO selectOne(int cartId) {
        return sqlSession.selectOne(NAMESPACE + "selectOne", cartId);
    }

    public void delete(int cartId) {
        sqlSession.delete(NAMESPACE + "delete", cartId);
    }

    public void update(CartDTO cartDTO) {
        sqlSession.update(NAMESPACE + "update", cartDTO);
    }
}
