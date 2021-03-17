package com.duktig.springboot.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * @Description: 分页处理工具
 * @Creator: flanderschuang
 * @Date: 2021/3/17 12:22 下午
 */
public class PageUtil extends LinkedHashMap<String, Object> {

    private int page;

    private int limit;

    public PageUtil(Map<String,Object> params) {
        this.putAll(params);

        // 分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put(("start"), (page - 1) * limit);
        this.put("page",page);
        this.put("limit", limit);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
