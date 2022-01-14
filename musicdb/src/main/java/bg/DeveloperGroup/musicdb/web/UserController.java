package bg.DeveloperGroup.musicdb.web;

import bg.DeveloperGroup.musicdb.Service.UserService;
import bg.DeveloperGroup.musicdb.models.Service.UserServiceRegistrationServiceModel;
import bg.DeveloperGroup.musicdb.models.binding.UserRegistrationBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService){
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @ModelAttribute("registrationBindingModel")
    public UserRegistrationBindingModel createBindingModel(){
        return new UserRegistrationBindingModel ();
    }

    @GetMapping("/login")
    public String login(){
        return "login";

    }
    @PostMapping("/login")
    public String loginConfirm(){
        return "home";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerAndLoginUser(
            @Valid UserRegistrationBindingModel registrationBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors ()){
            redirectAttributes.addFlashAttribute ("registrationBindingModel",registrationBindingModel);
            redirectAttributes.addFlashAttribute ("org.springframework.validation.BindingResult.registrationBindingModel", bindingResult);
            return "redirect:/users/register";
        }

        if(userService.userNameExists (registrationBindingModel.getUsername ())){
            redirectAttributes.addFlashAttribute ("registrationBindingModel",registrationBindingModel);
            redirectAttributes.addFlashAttribute ("userExistsError",true);
            return "redirect:/users/register";
        }

    UserServiceRegistrationServiceModel userServiceRegistrationServiceModel= modelMapper
            .map (registrationBindingModel, UserServiceRegistrationServiceModel.class);
    userService.registerAndLoginUser (userServiceRegistrationServiceModel);

    return "redirect:/home";
    }

    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute (UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                          String username,
                                    RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute ("bad_credentials", true);
        redirectAttributes.addFlashAttribute ("username", username);

        return ("redirect:/users/login");

    }

}
