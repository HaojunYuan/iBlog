package com.example.demo.query;

import java.io.Serializable;

public class Query<T> implements Serializable {

    public static final long DEFAULT_OFFSET = 10;

    public static final long MAX_OFFSET = 1000;

    private long page=1;

    /**
     * keyword
     */
    private String s;

    /**
     * select * from user limit 0,10
     */
    private long begin;

    private long limit = DEFAULT_OFFSET;

    private long offset;

    /**
     * select count(*) from user
     */
    private long total;

    /**
     *
     */
    private long totalPage = 1;

    private T items;

    public long getLimit() {
        if (offset<=0){
            limit = 0;
        } else if (offset > MAX_OFFSET){
            limit = offset = MAX_OFFSET;
        } else {
            limit = offset;
        }
        return limit;
    }

    public long getOffset() {
        if (offset>MAX_OFFSET){
            offset = MAX_OFFSET;
        }
        return offset;
    }

    public long getTotalPage() {
        if (getLimit()>0 && getTotal()>0){
            totalPage = (getTotal() % getLimit() == 0) ? (getTotal() / getLimit()) : (getTotal() / getLimit() + 1);
        }
        if (getPage() > totalPage){
            setPage(totalPage);
        }
        return totalPage;
    }

    public Query<T> limit(long limit) {
        this.limit = limit;
        return this;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public T getItems() {
        return items;
    }

    public Query<T> items(T items) {
        this.items = items;
        return this;
    }

    public long getBegin() {
        return begin;
    }

    public Query<T> begin(long begin) {
        this.begin = begin;
        return this;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getTotal() {
        return total;
    }

    public Query<T> total(long total) {
        this.total = total;
        return this;
    }

    public Query<T> totalPage(long totalPage) {
        this.totalPage = totalPage;
        return this;
    }
}
