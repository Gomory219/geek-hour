package cn.edu.sxu.gkh.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeDetailDTO {

    String businessId;

    Integer likeCount;

    Boolean isLiked;

}
