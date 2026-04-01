package cn.edu.sxu.gkhinteraction.service.impl;

import cn.edu.sxu.domain.enums.BusinessType;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LikeInnerServiceImplTest {

    @Resource
    private LikeInnerServiceImpl likeInnerServiceImpl;

    @Test
    void likeCount() {
        Map<String, Integer> stringIntegerMap = likeInnerServiceImpl.likeCount(List.of("c012"), BusinessType.COURSE);
        System.out.println(JSONUtil.toJsonPrettyStr(stringIntegerMap));
    }
}