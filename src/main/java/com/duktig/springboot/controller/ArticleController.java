package com.duktig.springboot.controller;

import com.duktig.springboot.common.Constants;
import com.duktig.springboot.common.Result;
import com.duktig.springboot.common.ResultGenerator;
import com.duktig.springboot.config.annotation.TokenToUser;
import com.duktig.springboot.entity.AdminUser;
import com.duktig.springboot.entity.Article;
import com.duktig.springboot.service.ArticleService;
import com.duktig.springboot.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /*
     * @Description: 查询页面列表信息
     * @Creator: flanderschuang
     * @Date: 2021/3/17 10:01 上午
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数错误");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultGenerator.getSuccessResult(articleService.getArticlePage(pageUtil));
    }
    /*
     * @Description: 查看文章详情
     * @Creator: flanderschuang
     * @Date: 2021/3/17 10:03 上午
     */
    @GetMapping("/info/{id]")
    public Result info(@PathVariable("id") Integer id) {
        Article article = articleService.queryObject(id);
        return ResultGenerator.getSuccessResult(article);
    }
    /*
     * @Description: 添加文章
     * @Creator: flanderschuang
     * @Date: 2021/3/17 10:06 上午
     */
    @PostMapping("/save")
    public Result save(@RequestBody Article article, @TokenToUser AdminUser loginUser) {
        if (article.getAddName() == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "作者不能为空，请添加作者");
        }
        if (loginUser == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "用户未登录");
        }
        if (articleService.saveArticle(article) > 0) {
            return ResultGenerator.getSuccessResult();
        } else {
            return ResultGenerator.getFailResult("添加失败");
        }
    }

    /*
     * @Description: 修改文章
     * @Creator: flanderschuang
     * @Date: 2021/3/17 10:07 上午
     */
    @PutMapping("/update")
    public Result update(@RequestBody Article article, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "用户未登录，请登录后重试");
        }
        if (articleService.updateArticle(article) > 0) {
            return ResultGenerator.getSuccessResult();
        } else {
            return ResultGenerator.getFailResult("修改失败");
        }
    }

    /*
     * @Description: 删除文章
     * @Creator: flanderschuang
     * @Date: 2021/3/17 10:09 上午
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestBody Integer[] ids, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_NOT_LOGIN,"用户暂未登录，请登录后重试");
        }
        if (ids.length < 1) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数错误");
        }
        if (articleService.deleteBatch(ids) > 0) {
            return ResultGenerator.getSuccessResult();
        } else {
            return ResultGenerator.getFailResult("删除失败");
        }
    }
}
