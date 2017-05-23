package com.xin.db.common;

import java.io.Serializable;

/**
 * @createTime: 2015年1月31日 下午3:01:41
 */
public final class Page implements Serializable {

    // 默认的序列化版本 id.
    private static final long serialVersionUID = 1L;
    // 分页查询开始记录位置
    private int begin;
    // 分页查看下结束位置.
    private int end;
    // 每页显示记录数
    private int size = 20;
    // 查询结果总记录数
    private int totalRecords;
    // 当前页码
    private int pageNo = 1;
    // 总共页数
    private int pageCount;

    public Page() {

    }

    /**
     * 设置页数，自动计算数据范围.
     *
     * @param pageNo
     */
    public Page(int pageNo, int size) {
        this.setPageParam(pageNo, size);
    }

    private void setPageParam(int pageNo, int size) {
        this.pageNo = pageNo;
        if (size == 0) {
            this.size = 20;
        } else {
            this.size = size;
        }

        pageNo = pageNo > 0 ? pageNo : 1;
        this.begin = this.size * (pageNo - 1);
        this.end = this.size * pageNo - 1;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.setPageParam(pageNo, size);
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.setPageParam(pageNo, size);
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getLimitBegin() {
        return (this.pageNo - 1) * this.size;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("begin=").append(begin)
                .append(", end=").append(end).append(", length=").append(size)
                .append(", totalRecords=").append(totalRecords)
                .append(", pageNo=").append(pageNo).append(", pageCount=")
                .append(pageCount);
        return builder.toString();
    }

}