package cn.edu.sxu.gkhinteraction.domain.dto;

import cn.edu.sxu.common.RollQueryRequest;
import cn.edu.sxu.domain.enums.BusinessType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentQueryDTO extends RollQueryRequest {

    private BusinessType businessType;
    private String businessId;
}
