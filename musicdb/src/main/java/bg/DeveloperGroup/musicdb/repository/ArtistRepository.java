package bg.DeveloperGroup.musicdb.repository;

import bg.DeveloperGroup.musicdb.models.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

    @Query("SELECT a.name FROM ArtistEntity a")
    List<String>findAllArtistNames();



    Optional<ArtistEntity> findByName(String artist);
}
