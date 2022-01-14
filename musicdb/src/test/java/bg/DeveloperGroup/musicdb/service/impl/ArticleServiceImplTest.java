package bg.DeveloperGroup.musicdb.service.impl;

import bg.DeveloperGroup.musicdb.Service.impl.ArticleServiceImpl;
import bg.DeveloperGroup.musicdb.models.entity.ArticleEntity;
import bg.DeveloperGroup.musicdb.models.entity.UserEntity;
import bg.DeveloperGroup.musicdb.models.entity.enums.Genre;
import bg.DeveloperGroup.musicdb.models.view.ArticleViewModel;
import bg.DeveloperGroup.musicdb.repository.ArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {

    private ArticleServiceImpl serviceToTest;
    private UserEntity testUser1, testUser2;
    private ArticleEntity testArticle1, testArticle2;


    @Mock
    ArticleRepository mockArticleRepository;

    @BeforeEach
    public void init(){
        testUser1 = new UserEntity();
        testUser1.setUsername("user 1");

        testArticle1 = new ArticleEntity();
        testArticle1
                .setTitle("article1")
                .setImageUrl("image1")
                .setGenre(Genre.JAZZ)
                .setContent("article1")
                .setUserEntity(testUser1);

        testUser2= new UserEntity();
        testUser2.setUsername("user 2");

        testArticle2 = new ArticleEntity();
        testArticle2
                .setTitle("article2")
                .setImageUrl("image2")
                .setGenre(Genre.CHALGICHKA)
                .setContent("article2")
                .setUserEntity(testUser2);

        when(mockArticleRepository.findAll()).thenReturn(List.of(testArticle1,testArticle2));

        serviceToTest = new ArticleServiceImpl(new ModelMapper(),mockArticleRepository);
    }

    @Test
    public void testFindAll(){
        List<ArticleViewModel> allModels = serviceToTest.findAllArticles();

        Assertions.assertEquals(2,allModels.size());

        ArticleViewModel model1 = allModels.get(0);
        ArticleViewModel model2 = allModels.get(1);

        //verify model 1
        Assertions.assertEquals(testArticle1.getTitle(),model1.getTitle());
        Assertions.assertEquals(testArticle1.getImageUrl(),model1.getImageUrl());
        Assertions.assertEquals(testArticle1.getContent(),model1.getContent());
        Assertions.assertEquals(testArticle1.getGenre(),model1.getGenre());
        Assertions.assertEquals(testUser1.getUsername(),model1.getAuthor());

        //verify model 2
        Assertions.assertEquals(testArticle2.getTitle(),model2.getTitle());
        Assertions.assertEquals(testArticle2.getImageUrl(),model2.getImageUrl());
        Assertions.assertEquals(testArticle2.getContent(),model2.getContent());
        Assertions.assertEquals(testArticle2.getGenre(),model2.getGenre());
        Assertions.assertEquals(testUser2.getUsername(),model2.getAuthor());


    }
}
