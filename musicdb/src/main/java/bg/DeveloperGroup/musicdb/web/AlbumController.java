package bg.DeveloperGroup.musicdb.web;

import bg.DeveloperGroup.musicdb.Service.AlbumService;
import bg.DeveloperGroup.musicdb.Service.ArtistService;
import bg.DeveloperGroup.musicdb.models.Service.AlbumServiceModel;
import bg.DeveloperGroup.musicdb.models.binding.AlbumBindingModel;
import bg.DeveloperGroup.musicdb.models.view.AlbumViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.ZoneId;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final ModelMapper modelMapper;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public AlbumController(ModelMapper modelMapper, AlbumService albumService, ArtistService artistService){
        this.modelMapper = modelMapper;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @ModelAttribute("albumBindingModel")
    public AlbumBindingModel createBindingModel(){
        return new AlbumBindingModel ();
    }

    @GetMapping("/add")
    public String showAddAlbum(Model model){

        model.addAttribute ("artists", artistService.findAllArtist ());

        return "add-album";
    }

    @PostMapping("/add")
    public String addAlbum (@Valid AlbumBindingModel albumBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            @AuthenticationPrincipal UserDetails principal){

        if(bindingResult.hasErrors ()){
            redirectAttributes.addFlashAttribute ("albumBindingModel",albumBindingModel);
            redirectAttributes.addFlashAttribute ("org.springframework.validation.BindingResult.albumBindingModel", bindingResult);
            return "redirect:/albums/add";
        }

        AlbumServiceModel albumServiceModel = modelMapper.
                map (albumBindingModel,
                        AlbumServiceModel.class);
        albumServiceModel.setUser (principal.getUsername ());
        //Parse from LocalDate -> Instant
        albumServiceModel.setReleaseDate (albumBindingModel.getReleaseDate ()
                .atStartOfDay (ZoneId.systemDefault ()).toInstant ());

        albumService.createAlbum (albumServiceModel);

        return "redirect:/home";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){

        AlbumViewModel albumViewModel = albumService.findById(id);
        model.addAttribute ("album", albumViewModel);

        return "details";

    }
}
