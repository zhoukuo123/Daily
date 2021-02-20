package com.zk.mgallery.utils;

import java.util.List;

// 分页模型对象
public class PageModel {
    private int page; //
    private int totalPages;
    private int rows;
    private int totalRows;
    private int pageStartRow;
    private int pageEndRow;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private List pageData;
}
