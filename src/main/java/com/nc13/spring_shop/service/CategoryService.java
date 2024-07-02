package com.nc13.spring_shop.service;

import com.nc13.spring_shop.model.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final SqlSession sqlSession;
    private final String NAMESPACE = "com.nc13.mappers.CategoryMapper.";

    public void insert(CategoryDTO categoryDTO) {
        sqlSession.insert(NAMESPACE + "insert", categoryDTO);
    }

    public List<CategoryDTO> selectAll() {
        return sqlSession.selectList(NAMESPACE + "selectAll");
    }

    public CategoryDTO selectOne(int categoryId) {
        return sqlSession.selectOne(NAMESPACE + "selectOne", categoryId);
    }

    public void update(CategoryDTO updateCategoryDTO) {
        sqlSession.update(NAMESPACE + "update", updateCategoryDTO);
    }

    public void delete(int categoryId) {
        sqlSession.delete(NAMESPACE + "delete", categoryId);
    }
}
