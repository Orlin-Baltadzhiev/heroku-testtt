package bg.DeveloperGroup.musicdb;

import bg.DeveloperGroup.musicdb.Service.ArtistService;
import bg.DeveloperGroup.musicdb.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MusicDbApplicationInit implements CommandLineRunner {

    private final UserService userService;
    private final ArtistService artistService;

    public MusicDbApplicationInit(UserService userService, ArtistService artistService){
        this.userService = userService;
        this.artistService = artistService;
    }

    @Override
    public void run(String... args) throws Exception{
        userService.seedUsers ();
        artistService.seedArtist ();
    }
}
