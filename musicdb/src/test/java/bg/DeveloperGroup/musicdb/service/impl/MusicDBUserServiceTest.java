package bg.DeveloperGroup.musicdb.service.impl;

import bg.DeveloperGroup.musicdb.Service.impl.MusicDbUserService;
import bg.DeveloperGroup.musicdb.models.entity.UserEntity;
import bg.DeveloperGroup.musicdb.models.entity.UserRoleEntity;
import bg.DeveloperGroup.musicdb.models.entity.enums.UserRole;
import bg.DeveloperGroup.musicdb.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class MusicDBUserServiceTest {

    private MusicDbUserService serviceTest;

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    public void setUp(){
        serviceTest = new MusicDbUserService(mockUserRepository);
    }

    @Test
    public void testUserNotFound(){
        Assertions.assertThrows(
                UsernameNotFoundException.class,() -> {
                    serviceTest.loadUserByUsername("user_does_not_exits");

                }
        );
    }

    @Test
    void testExistingUser(){
        UserEntity userEntity =new UserEntity();
        userEntity.setUsername("orlin1")
                .setPassword("xyz")
                .setFullname("Baltadzhiev");
        UserRoleEntity roleUser = new UserRoleEntity();
        roleUser.setRole(UserRole.USER);
        UserRoleEntity adminUser = new UserRoleEntity();
        adminUser.setRole(UserRole.ADMIN);
        userEntity.setRoles(List.of(roleUser,adminUser));

        Mockito.when(mockUserRepository.findByUsername("orlin1")).thenReturn(Optional.of(userEntity));

        UserDetails userDetails = serviceTest.loadUserByUsername("orlin1");

        Assertions.assertEquals(userEntity.getUsername(),userDetails.getUsername());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());

        List<String> authorities = userDetails.getAuthorities().
                stream().
                map(GrantedAuthority::getAuthority).
                collect(Collectors.toList());

        Assertions.assertTrue(authorities.contains("ROLE_ADMIN"));
        Assertions.assertTrue(authorities.contains("ROLE_USER"));

    }

}
