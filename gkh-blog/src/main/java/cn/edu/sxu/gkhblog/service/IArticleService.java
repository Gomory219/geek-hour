package cn.edu.sxu.gkhblog.service;

import cn.edu.sxu.common.PageRequest;
import cn.edu.sxu.gkhblog.domain.entity.Article;
import com.mongoplus.service.IService;

import java.util.List;

public interface IArticleService extends IService<Article> {
    List<Article> getFeaturedArticles(PageRequest pageRequest);
}
