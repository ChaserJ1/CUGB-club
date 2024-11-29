package edu.cugb.auth.common.entity;

/**
 * @Author pengjia
 * @Data 2024/10/28 15:31
 * @Description: 分页请求实体
 */
public class PageInfo {

    private Integer pageNo = 1;

    private Integer pageSize = 20;

    public Integer getPageNo() {
        if (pageNo == null || pageNo < 1) {
            return 1;
        }
        return pageNo;
    }

    public Integer getPageSize() {
        if (pageSize == null || pageSize < 1 || pageSize > Integer.MAX_VALUE) return 20;
        return pageSize;
    }
}
