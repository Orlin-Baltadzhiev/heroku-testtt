package bg.DeveloperGroup.musicdb.web;

import bg.DeveloperGroup.musicdb.models.entity.AlbumEntity;
import bg.DeveloperGroup.musicdb.models.entity.ArtistEntity;
import bg.DeveloperGroup.musicdb.models.entity.UserEntity;
import bg.DeveloperGroup.musicdb.models.entity.enums.Genre;
import bg.DeveloperGroup.musicdb.repository.AlbumRepository;
import bg.DeveloperGroup.musicdb.repository.ArtistRepository;
import bg.DeveloperGroup.musicdb.repository.LogRepository;
import bg.DeveloperGroup.musicdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

public class AlbumTestData {

    private long testAlbumId;

    private  AlbumRepository albumRepository;
    private UserRepository userRepository;
    private ArtistRepository artistRepository;
    private LogRepository logRepository;

    public AlbumTestData(AlbumRepository albumRepository,UserRepository userRepository,
                         ArtistRepository artistRepository,LogRepository logRepository) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
        this.logRepository = logRepository;
    }

    public void init() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("pesho")
                .setPassword("xyz")
                .setFullname("petar petrov");
        userEntity = userRepository.save(userEntity);


        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName("METALLICA");
        artistEntity.setCareerInformation("Some info about metallica");

        artistEntity = artistRepository.save(artistEntity);


        AlbumEntity justiceForAll = new AlbumEntity();
        justiceForAll.
                setName("JUSTICE FOR ALL").
                setImageUrl("https://upload.wikimedia.org/wikipedia/en/b/bd/Metallica_-_...And_Justice_for_All_cover.jpg").
                setVideoUrl("_fKAsvJrFes").
                setDescription("Sample description").
                setCopies(11).
                setPrice(BigDecimal.TEN).
                setReleaseDate(LocalDate.of(1988, 3, 3).atStartOfDay(ZoneId.systemDefault()).toInstant()).
                setGenre(Genre.METAL).
                setArtistEntity(artistEntity).
                setUserEntity(userEntity);

        justiceForAll = albumRepository.save(justiceForAll);
        testAlbumId = justiceForAll.getId();

        AlbumEntity masteredOfPuppets = new AlbumEntity();
        masteredOfPuppets.
                setName("MASTER OF PUPPETS").
                setImageUrl("https://upload.wikimedia.org/wikipedia/en/b/bd/Metallica_-_...And_Justice_for_All_cover.jpg").
                setVideoUrl("_fKAsvJrFes").
                setDescription("Sample description").
                setCopies(111).
                setPrice(BigDecimal.TEN).
                setReleaseDate(LocalDate.of(1988, 3, 3).atStartOfDay(ZoneId.systemDefault()).toInstant()).
                setGenre(Genre.METAL).
                setArtistEntity(artistEntity).
                setUserEntity(userEntity);

        masteredOfPuppets = albumRepository.save(masteredOfPuppets);

    }

    public long getTestAlbumId() {
        return testAlbumId;
    }

    public AlbumTestData setTestAlbumId(long testAlbumId) {
        this.testAlbumId = testAlbumId;
        return this;
    }

    void cleanUp(){
        logRepository.deleteAll();
        albumRepository.deleteAll();
        userRepository.deleteAll();
        artistRepository.deleteAll();
    }




}
