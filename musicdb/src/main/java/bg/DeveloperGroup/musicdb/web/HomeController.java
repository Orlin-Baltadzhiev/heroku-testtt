package bg.DeveloperGroup.musicdb.web;

import bg.DeveloperGroup.musicdb.Service.CarouselService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final CarouselService carouselService;


    public HomeController(CarouselService carouselService){
        this.carouselService = carouselService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal){
        model.addAttribute ("firstImg",carouselService.firstImage ());
        model.addAttribute ("secondImg",carouselService.secondImage ());
        model.addAttribute ("thirdImg",carouselService.thirdImage ());

        System.out.println(principal.getName());


        return "home";
    }
}
