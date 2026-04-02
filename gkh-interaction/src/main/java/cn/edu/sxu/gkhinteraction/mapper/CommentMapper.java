package cn.edu.sxu.gkhinteraction.mapper;

import cn.edu.sxu.gkhinteraction.domain.dto.CommentQueryDTO;
import cn.edu.sxu.gkhinteraction.domain.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2026-04-01
 */
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> selectCommentByDefault(CommentQueryDTO commentQueryDTO);
}
