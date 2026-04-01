package cn.edu.sxu.gkhblog.domain.vo;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleVO {
    String id;

    String userId;
    String categoryId;
    String title;
    String coverImage;

    String summary;

    List<String> tags;

    Integer viewCount;
    Integer commentCount;
    Integer likeCount;
    Integer favoriteCount;

    Boolean isLiked;
    Boolean isFavorite;

    LocalDateTime createTime;
    LocalDateTime publishTime;
    LocalDateTime updateTime;
}
