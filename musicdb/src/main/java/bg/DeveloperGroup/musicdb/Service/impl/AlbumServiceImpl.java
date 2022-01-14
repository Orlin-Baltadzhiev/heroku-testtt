package bg.DeveloperGroup.musicdb.Service.impl;

import bg.DeveloperGroup.musicdb.Service.AlbumService;
import bg.DeveloperGroup.musicdb.Service.ArtistService;
import bg.DeveloperGroup.musicdb.exceptions.ObjectNotFoundException;
import bg.DeveloperGroup.musicdb.models.Service.AlbumServiceModel;
import bg.DeveloperGroup.musicdb.models.entity.AlbumEntity;
import bg.DeveloperGroup.musicdb.models.entity.ArtistEntity;
import bg.DeveloperGroup.musicdb.models.entity.UserEntity;
import bg.DeveloperGroup.musicdb.models.view.AlbumViewModel;
import bg.DeveloperGroup.musicdb.repository.AlbumRepository;
import bg.DeveloperGroup.musicdb.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final ArtistService artistService;

    public AlbumServiceImpl(UserRepository userRepository, AlbumRepository albumRepository, ModelMapper modelMapper, ArtistService artistService){
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.artistService = artistService;
    }

    @Override
    public void createAlbum(AlbumServiceModel serviceModel){
        AlbumEntity albumEntity = modelMapper.map (serviceModel,AlbumEntity.class);
        UserEntity creator = userRepository.
                findByUsername (serviceModel.getUser ()).
                orElseThrow (() -> new IllegalArgumentException ("Creator " + serviceModel.getUser () + " cannot be found"));
        albumEntity.setUserEntity (creator);

        ArtistEntity artistEntity = artistService.
                findByName(serviceModel.getArtist ());
        albumEntity.setArtistEntity (artistEntity);
        albumRepository.save (albumEntity);
    }

    @Override
    public AlbumViewModel findById(Long id){
        return albumRepository.
                findById (id).
                map(albumEntity -> {
                    AlbumViewModel albumViewModel = modelMapper.
                            map (albumEntity,AlbumViewModel.class);

                    albumViewModel.setArtist (albumEntity.getArtistEntity ().getName ());
                    return albumViewModel;

                }).orElseThrow (ObjectNotFoundException :: new);
    }

    @Override
    public AlbumEntity findEntityById(Long albumId){
        return albumRepository.
                findById (albumId).
                orElseThrow (ObjectNotFoundException:: new);


    }
}
