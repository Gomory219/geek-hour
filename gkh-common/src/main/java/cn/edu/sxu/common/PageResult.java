package cn.edu.sxu.common;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private long pageNum;
    private long pageSize;
    private long totalPage;
    private List<T> data;
}
