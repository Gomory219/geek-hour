package cn.edu.sxu.gkhblog.controller;

import cn.edu.sxu.common.PageRequest;
import cn.edu.sxu.gkhblog.domain.entity.Article;
import cn.edu.sxu.gkhblog.service.IArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    private IArticleService articleService;

    @GetMapping("/featured")
    public List<Article> getFeaturedArticles(PageRequest pageRequest) {
        return articleService.getFeaturedArticles(pageRequest);
    }
}
