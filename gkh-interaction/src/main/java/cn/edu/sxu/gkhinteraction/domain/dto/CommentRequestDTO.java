package cn.edu.sxu.gkhinteraction.domain.dto;

import cn.edu.sxu.domain.enums.BusinessType;
import lombok.Data;

import java.util.List;

@Data
public class CommentRequestDTO {

    private BusinessType businessType;
    private String businessId;
    private String parentId;
    private String replyUserId;
    private Boolean isMarkdown;
    private String content;
    private String targetReplyId;
    private List<String> imageUrls;
}
