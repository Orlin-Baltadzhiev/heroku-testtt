package bg.DeveloperGroup.musicdb.repository;

import bg.DeveloperGroup.musicdb.models.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository <LogEntity,Long> {
}
