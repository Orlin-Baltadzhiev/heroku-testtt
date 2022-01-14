package bg.DeveloperGroup.musicdb.Service.impl;

import bg.DeveloperGroup.musicdb.Service.AlbumService;
import bg.DeveloperGroup.musicdb.Service.LogService;
import bg.DeveloperGroup.musicdb.Service.UserService;
import bg.DeveloperGroup.musicdb.models.Service.LogServiceModel;
import bg.DeveloperGroup.musicdb.models.Service.UserServiceModel;
import bg.DeveloperGroup.musicdb.models.entity.AlbumEntity;
import bg.DeveloperGroup.musicdb.models.entity.LogEntity;
import bg.DeveloperGroup.musicdb.models.entity.UserEntity;
import bg.DeveloperGroup.musicdb.repository.LogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private final AlbumService albumService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public LogServiceImpl(LogRepository logRepository, AlbumService albumService, UserService userService, ModelMapper modelMapper){
        this.logRepository = logRepository;
        this.albumService = albumService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createLog(String action, Long albumId){
        AlbumEntity albumEntity = albumService.findEntityById(albumId);

        Authentication authentication =SecurityContextHolder.
                getContext ().
                getAuthentication ();

        String username = authentication.getName ();
        UserEntity userEntity = userService.findUserEntityByName(username);


        LogEntity logEntity = new LogEntity ()
                .setAlbumEntity (albumEntity)
                .setUserEntity (userEntity)
                .setAction (action)
                .setDateTime (LocalDateTime.now ());

        logRepository.save (logEntity);

    }

    @Override
    public List<LogServiceModel> findAllLogs(){
        return logRepository.findAll ().stream ().map (logEntity -> {
            LogServiceModel logServiceModel = modelMapper.map (logEntity, LogServiceModel.class);
            logServiceModel.setAlbum (logEntity.getAlbumEntity ().getName ())
                    .setUser (logEntity.getUserEntity ().getUsername ());
            return logServiceModel;
        }).collect(Collectors.toList());
    }
}
