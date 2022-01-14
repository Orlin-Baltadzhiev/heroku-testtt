package bg.DeveloperGroup.musicdb.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ModelMapper modelMapper;

    public ArticleController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String getAllArticles(){

        return "all-articles";
    }

    @GetMapping("/add")
    public String addArticle(){
        return "add-article";
    }

}
