package cn.edu.sxu.gkhcourse.domain.vo;

import cn.edu.sxu.gkhcourse.domain.enums.TutorialType;
import lombok.Data;

import java.util.List;

@Data
public class TutorialBriefVO {

    private String id;

    private String title;

    private TutorialType type;

    private String parentTutorialId; // 父教程ID

    private List<TutorialBriefVO> children;

    private boolean isTrail;
}
