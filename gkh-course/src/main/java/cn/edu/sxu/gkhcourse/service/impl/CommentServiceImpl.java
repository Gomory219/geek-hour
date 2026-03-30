package cn.edu.sxu.gkhcourse.service.impl;

import cn.edu.sxu.gkhcourse.domain.entity.Comment;
import cn.edu.sxu.gkhcourse.mapper.CommentMapper;
import cn.edu.sxu.gkhcourse.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
