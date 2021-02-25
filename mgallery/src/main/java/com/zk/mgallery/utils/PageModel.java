package com.zk.mgallery.utils;

import com.zk.mgallery.entity.Painting;

import java.util.List;

// 分页模型对象
public class PageModel {
    private int page; // 当前页号
    private int totalPages; // 总页数
    private int rows; // 每页记录数
    private int totalRows; // 总记录数
    private int pageStartRow; // 当前页从第n行开始
    private int pageEndRow; // 当前页到第n行结束
    private boolean hasNextPage; // 是否存在下一页
    private boolean hasPreviousPage; // 是否存在上一页
    private List<Painting> pageData; // 当前页面数据

    public PageModel() {

    }

    public PageModel(List<Painting> data, int page, int rows) {
        this.page = page;
        this.rows = rows;
        totalRows = data.size();
        // 总页数计算规则: 总行数/每页记录数, 能整除页数取整, 不能整除向上取整
        // 例如: 18 / 6 = 3 | 20 / 6 = 3.33 向上取整 = 4
        // Math.ceil 浮点数向上取整   Math.floor 浮点数向下取整 返回double类型
        totalPages = new Double(Math.ceil((float)totalRows / rows)).intValue();

        pageStartRow = (page - 1) * rows; // 0
        pageEndRow = page * rows; // 6
        // totalRows: 20 | totalPage: 4 | rows: 6
        // pageEndRow = 4 * 6 = 24 > 20 执行subList抛出下标越界异常
        if (pageEndRow > totalRows) {
            pageEndRow = totalPages;
        }
        pageData =  data.subList(pageStartRow, pageEndRow); // 得到分页数据
        if (page > 1) {
            hasPreviousPage = true;
        } else {
            hasPreviousPage = false;
        }
        if (page < totalPages) {
            hasNextPage = true;
        } else {
            hasNextPage = false;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getPageStartRow() {
        return pageStartRow;
    }

    public void setPageStartRow(int pageStartRow) {
        this.pageStartRow = pageStartRow;
    }

    public int getPageEndRow() {
        return pageEndRow;
    }

    public void setPageEndRow(int pageEndRow) {
        this.pageEndRow = pageEndRow;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public List<Painting> getPageData() {
        return pageData;
    }

    public void setPageData(List<Painting> pageData) {
        this.pageData = pageData;
    }
}
