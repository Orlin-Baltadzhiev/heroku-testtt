package bg.DeveloperGroup.musicdb.web;

import bg.DeveloperGroup.musicdb.Service.LogService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatsController {

    private final LogService logService;
    private final ModelMapper modelMapper;

    public StatsController(LogService logService, ModelMapper modelMapper){
        this.logService = logService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String stats(Model model){

        model.addAttribute ("logs",logService.findAllLogs());


        return "stats";
    }
}
