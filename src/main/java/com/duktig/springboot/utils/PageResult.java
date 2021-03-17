package com.duktig.springboot.utils;

import java.io.Serializable;
import java.util.List;

/*
 * @Description: 页面数据处理工具
 * @Creator: flanderschuang
 * @Date: 2021/3/17 12:22 下午
 */
public class PageResult implements Serializable {
    // 总计录数
    private int totalCount;
    // 每页记录数
    private int pageSize;
    // 总页数
    private int totalPage;
    // 当前页数
    private int currPage;
    // 列表数据
    // 无法确定传入的参数是 Number 的子类 还是 String 类型，这里用来限定者；两种类型
    private List<? extends Serializable> list;

    public PageResult(List<? extends Serializable> list, int totalCount, int pageSize,  int currPage) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        // Math.ceil() 方法可对一个数进行上舍入，返回值（double 类型）大于或等于给定的参数。
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);;
        this.currPage = currPage;
        this.list = list;
    }


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<? extends Serializable> getList() {
        return list;
    }

    public void setList(List<? extends Serializable> list) {
        this.list = list;
    }
}
