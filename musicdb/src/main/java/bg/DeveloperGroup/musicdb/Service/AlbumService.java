package bg.DeveloperGroup.musicdb.Service;

import bg.DeveloperGroup.musicdb.models.Service.AlbumServiceModel;
import bg.DeveloperGroup.musicdb.models.entity.AlbumEntity;
import bg.DeveloperGroup.musicdb.models.view.AlbumViewModel;

public interface AlbumService {

    void createAlbum(AlbumServiceModel albumServiceModel);

    AlbumViewModel findById(Long id);

    AlbumEntity findEntityById(Long albumId);
}
