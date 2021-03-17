package com.duktig.springboot.dao;

import com.duktig.springboot.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ArticleDao {
    // 返回包含所有文章的集合
    List<Article> findArticles(Map<String, Object> map);

    // 数据数目
    int getTotalArticles(Map<String, Object> map);

    // 添加
    int insertArticle(Article article);

    // 修改
    int updateArticle(Article article);

    // 删除
    int deleteArticle(Integer id);

    // 根据 id 查找
    Article getArticleById(Integer id);

    // 批量删除
    int deleteBatch(Object[] ids);
}
