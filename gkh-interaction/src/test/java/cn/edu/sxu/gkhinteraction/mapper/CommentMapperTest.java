package cn.edu.sxu.gkhinteraction.mapper;

import cn.edu.sxu.gkhinteraction.domain.entity.Comment;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CommentMapperTest {

    @Resource
    private CommentMapper commentMapper;

    @Test
    public void test() {
        Comment comment = commentMapper.selectById("123");
        log.info(JSONUtil.toJsonPrettyStr(comment));
    }

}