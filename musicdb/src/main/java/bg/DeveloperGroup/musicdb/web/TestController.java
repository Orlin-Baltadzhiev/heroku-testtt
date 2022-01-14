package bg.DeveloperGroup.musicdb.web;

import bg.DeveloperGroup.musicdb.Service.UserService;
import bg.DeveloperGroup.musicdb.models.Service.UserServiceModel;
import bg.DeveloperGroup.musicdb.models.binding.UserChangeProfileBindingModel;
import bg.DeveloperGroup.musicdb.models.binding.UserRegistrationBindingModel;
import bg.DeveloperGroup.musicdb.models.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/test")
public class TestController {
    private  final UserService userService;
    private final ModelMapper modelMapper;

    public TestController(UserService userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @ModelAttribute("userChangeProfileBindingModel")
    public UserChangeProfileBindingModel userChangeProfileBindingModel(){
        return new UserChangeProfileBindingModel ();
    }

    @GetMapping("/update")
    public String update(Principal principal, Model model){
        UserServiceModel userServiceModel = userService.findByName (principal.getName ());
        Long id = userService.findIdByName(userServiceModel.getUsername ());
        model.addAttribute ("username", userServiceModel.getUsername ());
        model.addAttribute ("userId", id);
        System.out.println ();
        return "test";
    }

    @PatchMapping("/update/{id}")
    public String updateConfirm(@PathVariable Long id,
                                UserChangeProfileBindingModel userChangeProfileBindingModel){

        //ToDo -> Method work correctly. After modify my Entity intellij can't find changes because MySQL is not synchronise.
        // And can't refresh changes in Database. It's better to change MySQL with PostgreSQL
        //


        userService.changeUserName(id,userChangeProfileBindingModel.getUsername ());
        System.out.println ();
        return "redirect:/home";

    }

}
