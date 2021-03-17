package com.duktig.springboot.service;

import com.duktig.springboot.entity.Article;
import com.duktig.springboot.utils.PageResult;
import com.duktig.springboot.utils.PageUtil;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    // 查询所有数据
    PageResult getArticlePage(PageUtil pageUtil);
   // 根据 id 查询数据
    Article queryObject(Integer id);
    // 查询列表
    List<Article> queryList(Map<String, Object> map);
    // 查询总数
    int queryTotal(Map<String, Object> map);
    // 保存文章
    int saveArticle(Article article);
    // 更新文章
    int updateArticle(Article article);
    // 通过 id 删除文章
    int deleteArticleById(Integer id);
    // 批量删除文章
    int deleteBatch(Integer[] ids);
}
