package com.duktig.springboot.service.impl;

import com.duktig.springboot.dao.ArticleDao;
import com.duktig.springboot.entity.Article;
import com.duktig.springboot.service.ArticleService;
import com.duktig.springboot.utils.PageResult;
import com.duktig.springboot.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service("articleService")
public class ArticleServerImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;
    @Override
    public PageResult getArticlePage(PageUtil pageUtil) {
        List<Article> articleList = articleDao.findArticles(pageUtil);
        int total = articleDao.getTotalArticles(pageUtil);
        PageResult pageResult = new PageResult(articleList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Article queryObject(Integer id) {
        Article article = articleDao.getArticleById(id);
        if (article != null) {
            return article;
        }
        return null;
    }

    @Override
    public List<Article> queryList(Map<String, Object> map) {
        List<Article> articles = articleDao.findArticles(map);
        return articles;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return articleDao.getTotalArticles(map);
    }
    @Override
    public int saveArticle(Article article) {
        return articleDao.insertArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        article.setUpdateTime(new Date());
        return articleDao.updateArticle(article);
    }

    @Override
    public int deleteArticleById(Integer id) {
        return articleDao.deleteArticle(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return articleDao.deleteBatch(ids);
    }
}
