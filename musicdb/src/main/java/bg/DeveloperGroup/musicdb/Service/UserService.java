package bg.DeveloperGroup.musicdb.Service;

import bg.DeveloperGroup.musicdb.models.Service.UserServiceModel;
import bg.DeveloperGroup.musicdb.models.Service.UserServiceRegistrationServiceModel;
import bg.DeveloperGroup.musicdb.models.entity.UserEntity;

public interface UserService {

    void seedUsers();

    void registerAndLoginUser(UserServiceRegistrationServiceModel serviceModel);

    boolean userNameExists(String username);

    UserServiceModel findByName (String username);

    UserEntity findById(Long id);

    Long findIdByName(String username);

    UserEntity findUserEntityByName(String username);


    void changeUserName(Long id, String username);
}
