package cn.edu.sxu.gkhblog.service.impl;

import cn.edu.sxu.common.PageRequest;
import cn.edu.sxu.gkhblog.domain.entity.Article;
import cn.edu.sxu.gkhblog.service.IArticleService;
import com.mongoplus.model.PageParam;
import com.mongoplus.model.PageResult;
import com.mongoplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<Article> implements IArticleService {
    @Override
    public List<Article> getFeaturedArticles(PageRequest pageRequest) {
        PageResult<Article> page = page(new PageParam(pageRequest.getPageNum(), pageRequest.getPageSize()));
        return page.getContentData();
    }
}
