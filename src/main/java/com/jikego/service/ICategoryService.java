package com.jikego.service;

import com.jikego.common.ServerResponse;
import com.jikego.pojo.Category;

import java.util.List;

/**
 * @Author: Geekerstar(jikewenku.com)
 * @Date: 2018/6/23 11:11
 * @Description:
 */
public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

}
