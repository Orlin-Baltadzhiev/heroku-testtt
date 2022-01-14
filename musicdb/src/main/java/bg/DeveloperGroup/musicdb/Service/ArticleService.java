package bg.DeveloperGroup.musicdb.Service;

import bg.DeveloperGroup.musicdb.models.view.ArticleViewModel;

import java.util.List;

public interface ArticleService {
    List<ArticleViewModel> findAllArticles();

}


