package cn.edu.sxu.gkhcourse.domain.vo;

import cn.edu.sxu.gkhcourse.domain.enums.TutorialType;
import lombok.Data;

@Data
public class TutorialDetailVO {

    private String id;

    private String title;

    private TutorialType type;

    private String content;

    private String videoUrl;

    private Integer likes;

    private Integer viewCount;

    private String authorId;

    private String authorName;

    private String authorAvatarUrl;

    private boolean isLiked;

}
