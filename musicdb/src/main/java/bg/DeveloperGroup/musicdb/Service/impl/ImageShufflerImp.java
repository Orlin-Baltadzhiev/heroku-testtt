package bg.DeveloperGroup.musicdb.Service.impl;

import bg.DeveloperGroup.musicdb.Service.ImageShuffler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ImageShufflerImp implements ImageShuffler {


    @Override
    public void shuffle(List<String> images) {
        Collections.shuffle (images);
    }
}
