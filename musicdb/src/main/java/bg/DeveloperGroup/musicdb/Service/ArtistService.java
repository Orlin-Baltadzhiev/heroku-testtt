package bg.DeveloperGroup.musicdb.Service;

import bg.DeveloperGroup.musicdb.models.entity.ArtistEntity;
import bg.DeveloperGroup.musicdb.models.view.ArtistViewModel;

import java.util.*;

public interface ArtistService {

    void seedArtist();

    List<String> findAllArtist();

    ArtistEntity findByName(String artist);
}
