package com.nc13.spring_shop.service;

import com.nc13.spring_shop.model.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final SqlSession sqlSession;
    private final int PAGE_SIZE = 20;
    private final String NAMESPACE = "com.nc13.mappers.ItemMapper.";

    public void insert(ItemDTO itemDTO) {
        sqlSession.insert(NAMESPACE + "insert", itemDTO);
    }

    public List<ItemDTO> selectAllByCategory(int pageNo, int categoryId) {

        Map<String, Integer> params = new HashMap<>();
        params.put("categoryId", categoryId);
        params.put("startRow", (pageNo - 1) * PAGE_SIZE);
        params.put("size", PAGE_SIZE);

        return sqlSession.selectList(NAMESPACE + "selectAllByCategory", params);
    }

    public List<ItemDTO> selectAll(int pageNo) {
        HashMap<String, Integer> paramMap = new HashMap<>();
        paramMap.put("startRow", (pageNo - 1) * PAGE_SIZE);
        paramMap.put("size", PAGE_SIZE);

        return sqlSession.selectList(NAMESPACE + "selectAll", paramMap);
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

    public void updateQuantity(ItemDTO itemDTO) {
        sqlSession.update(NAMESPACE + "updateQuantity", itemDTO);
    }

    public List<ItemDTO> selectAllBySellerId(int memberId) {
        return sqlSession.selectList(NAMESPACE + "selectAllByMemberId", memberId);
    }

    public int selectMaxPage() {
        int maxRow = sqlSession.selectOne(NAMESPACE + "selectMaxRow");

        int maxPage = maxRow / PAGE_SIZE;

        if (maxRow % PAGE_SIZE != 0) {
            maxPage++;
        }

        return maxPage;
    }
}
