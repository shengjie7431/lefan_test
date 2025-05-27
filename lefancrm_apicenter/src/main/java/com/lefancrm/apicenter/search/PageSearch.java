package com.lefancrm.apicenter.search;

/**
 * Created by lixianfeng on 2018/3/26.
 */
public class PageSearch {
    private int page;
    private int pageSize;
    private int pageIndex;
    private int backEndPageSize = 20;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getBackEndPageSize() {
        return backEndPageSize;
    }

    public void setBackEndPageSize(int backEndPageSize) {
        this.backEndPageSize = backEndPageSize;
    }
}
