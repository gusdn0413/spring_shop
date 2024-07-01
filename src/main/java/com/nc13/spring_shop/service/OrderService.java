package com.nc13.spring_shop.service;

import com.nc13.spring_shop.model.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final SqlSession sqlSession;
    private final String NAMESPACE = "com.nc13.mappers.OrderMapper.";

    public void insert(OrderDTO orderDTO) {
        sqlSession.insert(NAMESPACE + "insert", orderDTO);
    }

    public List<OrderDTO> selectAllByCustomer(int memberId) {
        return sqlSession.selectList(NAMESPACE + "selectAllByCustomer", memberId);
    }

    public List<OrderDTO> selectAllBySeller(int memberId) {
        return sqlSession.selectList(NAMESPACE + "selectAllBySeller", memberId);
    }

    public OrderDTO selectOne(int orderId) {
        return sqlSession.selectOne(NAMESPACE + "selectOne", orderId);
    }

    public void delete(int orderId) {
        sqlSession.delete(NAMESPACE + "delete", orderId);
    }

    public void update(OrderDTO updateOrder) {
        sqlSession.update(NAMESPACE + "update", updateOrder);
    }
}
