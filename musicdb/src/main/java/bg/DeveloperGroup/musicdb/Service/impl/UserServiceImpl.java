package bg.DeveloperGroup.musicdb.Service.impl;

import bg.DeveloperGroup.musicdb.Service.UserService;
import bg.DeveloperGroup.musicdb.models.Service.UserServiceModel;
import bg.DeveloperGroup.musicdb.models.Service.UserServiceRegistrationServiceModel;
import bg.DeveloperGroup.musicdb.models.entity.UserEntity;
import bg.DeveloperGroup.musicdb.models.entity.UserRoleEntity;
import bg.DeveloperGroup.musicdb.models.entity.enums.UserRole;
import bg.DeveloperGroup.musicdb.repository.UserRepository;
import bg.DeveloperGroup.musicdb.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final MusicDbUserService musicDbUserService;

    public UserServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, MusicDbUserService musicDbUserService){
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.musicDbUserService = musicDbUserService;
    }

    @Override
    public void seedUsers(){
        if (userRepository.count () == 0) {
            UserRoleEntity adminRole = new UserRoleEntity ().setRole (UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity ().setRole (UserRole.USER);

            userRoleRepository.saveAll (List.of (adminRole, userRole));


            UserEntity admin = new UserEntity ().setUsername ("admin").setPassword (passwordEncoder.encode ("topsecret"));
            UserEntity user = new UserEntity ().setUsername ("user").setPassword (passwordEncoder.encode ("topsecret"));
            admin.setRoles (List.of (adminRole, userRole));
            user.setRoles (List.of (userRole));
            admin.setFullname ("Baltadzhiev");
            user.setFullname ("Draganov");

            userRepository.save (admin);
            userRepository.save (user);
        }

    }

    @Override
    public void registerAndLoginUser(UserServiceRegistrationServiceModel serviceModel){
        UserEntity newUser = modelMapper.map (serviceModel, UserEntity.class);
        newUser.setPassword (passwordEncoder.encode (newUser.getPassword ()));

        UserRoleEntity userRoleOpt = userRoleRepository.
                findByRole (UserRole.USER).orElseThrow (
                        () -> new IllegalStateException ("USER role not found. Please seed the roles"));

        newUser.addRole (userRoleOpt);

        newUser = userRepository.save (newUser);

        UserDetails principal = musicDbUserService.loadUserByUsername (newUser.getUsername ());

        Authentication authentication = new UsernamePasswordAuthenticationToken (
                principal,
                newUser.getPassword (),
                principal.getAuthorities ()
        );

        SecurityContextHolder.getContext ().setAuthentication (authentication);


    }

    @Override
    public boolean userNameExists(String username){
        return userRepository.findByUsername (username).isPresent ();
    }

    @Override
    public UserServiceModel findByName(String username){
        return userRepository.
                findByUsername (username).
                map (userEntity -> modelMapper.map (userEntity, UserServiceModel.class))
                .orElseThrow (() -> new IllegalArgumentException ("There is no such username!"));

    }

    @Override
    public UserEntity findById(Long id){
        return userRepository.findById (id).orElseThrow (() ->
                new IllegalStateException ("\n" + "There is no such user" + userRepository.findById (id)));
    }

    @Override
    public Long findIdByName(String username){
        return userRepository.findByUsername (username).get ().getId ();
    }

    @Override
    public UserEntity findUserEntityByName(String username){
        return userRepository.findByUsername (username).orElseThrow (() ->
                new IllegalStateException ("\n" + "There is no such user" + userRepository.findByUsername (username)));
    }

    @Override
    public void changeUserName(Long id, String username){
        UserEntity userEntity = userRepository.findById (id).orElseThrow (() ->
                new IllegalStateException ("\n" + "There is no such user" + userRepository.findById (id)));
        userEntity.setUsername (username);
        userRepository.save (userEntity);

    }


}

