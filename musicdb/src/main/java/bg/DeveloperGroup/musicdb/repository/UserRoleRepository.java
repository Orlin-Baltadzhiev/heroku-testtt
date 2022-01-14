package bg.DeveloperGroup.musicdb.repository;

import bg.DeveloperGroup.musicdb.models.entity.UserRoleEntity;
import bg.DeveloperGroup.musicdb.models.entity.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository <UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByRole (UserRole role);
}
