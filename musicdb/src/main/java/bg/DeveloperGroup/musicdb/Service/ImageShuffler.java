package bg.DeveloperGroup.musicdb.Service;

import org.springframework.stereotype.Component;

import java.util.List;


public interface ImageShuffler {

    void shuffle(List<String> images);
}
