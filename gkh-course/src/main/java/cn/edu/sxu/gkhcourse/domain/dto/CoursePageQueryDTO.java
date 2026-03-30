package cn.edu.sxu.gkhcourse.domain.dto;

import cn.edu.sxu.common.PageRequest;
import cn.edu.sxu.gkhcourse.domain.enums.CourseLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CoursePageQueryDTO extends PageRequest {

    private String keyWord;

    private boolean isVIP;

    private boolean isFree;

    private Integer category;

    private CourseLevel level;

}
