package com.jikego.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jikego.common.ServerResponse;
import com.jikego.dao.CategoryMapper;
import com.jikego.pojo.Category;
import com.jikego.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Set;


/**
 * @author Geekerstar(jikewenku.com)
 * Date: 2018/6/23 11:12
 * Description:
 */
@Service("iCategoryService")
@Slf4j
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * description: 添加分类
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:26
     * param: [categoryName, parentId]
     * return: com.jikego.common.ServerResponse
     */
    @Override
    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }

        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        //这个分类是可用的
        category.setStatus(true);

        int rowCount = categoryMapper.insert(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    /**
     * description: 更新分类名称
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:26
     * param: [categoryId, categoryName]
     * return: com.jikego.common.ServerResponse
     */
    @Override
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新品类名字成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名字失败");
    }

    /**
     * description: 获取孩子节点的分类信息
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:26
     * param: [categoryId]
     * return: com.jikego.common.ServerResponse<java.util.List<com.jikego.pojo.Category>>
     */
    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            log.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    /**
     * description: 递归查询本节点的id及孩子节点的id
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:27
     * param: [categoryId]
     * return: com.jikego.common.ServerResponse<java.util.List<java.lang.Integer>>
     */
    @Override
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId);

        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null) {
            for (Category categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    /**
     * description: 递归算法，算出子节点
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:27
     * param: [categorySet, categoryId]
     * return: java.util.Set<com.jikego.pojo.Category>
     */
    private Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        //查找子节点，递归算法一定要有一个退出的条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getId());
        }
        return categorySet;
    }


}
