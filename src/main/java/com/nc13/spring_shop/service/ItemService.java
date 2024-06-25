package com.nc13.spring_shop.service;

import com.nc13.spring_shop.model.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final SqlSession sqlSession;
    private final String NAMESPACE = "com.nc13.mappers.ItemMapper.";

    public void insert(ItemDTO itemDTO) {
        sqlSession.insert(NAMESPACE + "insert", itemDTO);
    }
    public List<ItemDTO> selectAllByCategory(int categoryId) {
        return sqlSession.selectList(NAMESPACE + "selectAllByCategory", categoryId);
    }

    public List<ItemDTO> selectAll() {
        return sqlSession.selectList(NAMESPACE + "selectAll");
    }

    public ItemDTO selectOne(int itemId) {
        return sqlSession.selectOne(NAMESPACE + "selectOne", itemId);
    }

    public void delete(int itemId) {
        sqlSession.delete(NAMESPACE + "delete", itemId);
    }

    public void update(ItemDTO itemDTO) {
        sqlSession.update(NAMESPACE + "update", itemDTO);
    }
}
