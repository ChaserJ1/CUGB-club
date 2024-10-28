package edu.cugb.subject.common.entity;

import java.net.Inet4Address;
import java.util.Collections;
import java.util.List;

/**
 * @Author pengjia
 * @Data 2024/10/28 16:21
 * @Description: 分页返回的实体
 */
public class PageResult<T> {
    /**
     * 当前页
     */
    private Integer pageNo = 1;

    /**
     * 页面大小
     */
    private Integer pageSize = 20;

    /**
     * 总记录数
     */
    private Integer total = 0;

    /**
     * 总页数
     */
    private Integer totalPages = 0;

    /**
     * 回传的所有结果
     */
    private List<T> result = Collections.emptyList();

    /**
     * 当前页起始记录
     */
    private int start = 1;

    /**
     * 当前页结束记录
     */
    private int end = 0;

    /**
     * 设置当前记录
     *
     * @param result
     */
    public void setRecords(List<T> result) {
        this.result = result;
        if (result != null && !result.isEmpty()) {
            setTotal(result.size());
        }
    }

    /**
     * 设置总记录数以及总页数、起始页、结束页
     *
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
        if (this.pageSize > 0) {
            this.totalPages = (total / this.pageSize) + (total % this.pageSize == 0 ? 0 : 1);
        } else {
            this.totalPages = 0;
        }

        this.start = (this.pageSize > 0 ? (this.pageNo - 1) * this.pageSize : 0) + 1;
        this.end = (this.start - 1 + this.pageSize * (this.pageNo > 0 ? 1 : 0));
    }

    private void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    private void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }


}
