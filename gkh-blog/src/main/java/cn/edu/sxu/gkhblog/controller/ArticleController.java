package cn.edu.sxu.gkhblog.controller;

import cn.edu.sxu.common.PageRequest;
import cn.edu.sxu.gkhblog.domain.entity.Article;
import cn.edu.sxu.gkhblog.service.IArticleService;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ArticleController {

    @Resource
    private IArticleService articleService;

    @GetMapping("/featured")
    public List<Article> getFeaturedArticles(PageRequest pageRequest) {
        log.info("Getting featured articles with page request: {}", JSONUtil.toJsonPrettyStr(pageRequest));
        return articleService.getFeaturedArticles(pageRequest);
    }
}
