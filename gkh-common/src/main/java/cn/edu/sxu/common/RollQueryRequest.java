package cn.edu.sxu.common;

import lombok.Data;

@Data
public class RollQueryRequest {
    public static final Integer DEFAULT_RECORD_SIZE = 10;

    private String lastId;
    private String sortField;
    private String sortType;
}
