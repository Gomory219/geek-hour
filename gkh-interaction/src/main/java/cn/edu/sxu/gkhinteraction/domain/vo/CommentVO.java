package cn.edu.sxu.gkhinteraction.domain.vo;


import lombok.Data;

import java.util.List;

@Data
public class CommentVO {
    private String id;
    private String content;
    private Boolean isMarkDown;
    private List<String> imageUrls;

    private String userId;
    private String userName;
    private String userAvatar;
    private Integer likeNum;
    private Integer replyNum;

    private List<ReplyBriefVO> showReplies;
}
