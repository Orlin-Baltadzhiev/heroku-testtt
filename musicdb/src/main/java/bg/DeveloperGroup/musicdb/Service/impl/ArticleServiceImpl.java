package bg.DeveloperGroup.musicdb.Service.impl;

import bg.DeveloperGroup.musicdb.Service.ArticleService;
import bg.DeveloperGroup.musicdb.models.view.ArticleViewModel;
import bg.DeveloperGroup.musicdb.repository.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ModelMapper modelMapper, ArticleRepository articleRepository) {
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
    }


    @Override
    public List<ArticleViewModel> findAllArticles(){
        return articleRepository.findAll()
                .stream()
                .map(ae ->{
                    ArticleViewModel articleViewModel = modelMapper.map(ae,ArticleViewModel.class);
                    articleViewModel.setAuthor(ae.getUserEntity().getUsername());
                    return articleViewModel;
                })
                .collect(Collectors.toList());

    }
}
