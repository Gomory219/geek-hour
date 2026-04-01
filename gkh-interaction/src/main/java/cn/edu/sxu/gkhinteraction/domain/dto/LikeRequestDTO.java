package cn.edu.sxu.gkhinteraction.domain.dto;

import cn.edu.sxu.domain.enums.BusinessType;
import lombok.Data;

@Data
public class LikeRequestDTO {
    private BusinessType businessType;
    private String businessId;
}
