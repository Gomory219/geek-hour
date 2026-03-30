package cn.edu.sxu.gkhblog.domain.entity;

import cn.edu.sxu.domain.enums.ReleaseStatus;
import com.mongoplus.annotation.ID;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Article {

    @ID
    String id;

    String userId;
    String categoryId;
    String title;
    String coverImage;

    String summary;
    String content;

    List<String> tags;
    ReleaseStatus status;

    Integer viewCount;
    Integer commentCount;
    Integer likeCount;
    Integer favoriteCount;

    LocalDateTime updateDateTime;
    LocalDateTime createDateTime;
    LocalDateTime publishDateTime;

    Integer isDeleted;
}
