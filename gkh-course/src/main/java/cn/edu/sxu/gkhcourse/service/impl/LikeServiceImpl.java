package cn.edu.sxu.gkhcourse.service.impl;

import cn.edu.sxu.gkhcourse.domain.entity.Like;
import cn.edu.sxu.gkhcourse.mapper.LikeMapper;
import cn.edu.sxu.gkhcourse.service.ILikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 点赞表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements ILikeService {

}
