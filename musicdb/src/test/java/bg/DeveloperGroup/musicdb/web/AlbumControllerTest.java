package bg.DeveloperGroup.musicdb.web;

import bg.DeveloperGroup.musicdb.models.entity.AlbumEntity;
import bg.DeveloperGroup.musicdb.models.entity.ArtistEntity;
import bg.DeveloperGroup.musicdb.models.entity.UserEntity;
import bg.DeveloperGroup.musicdb.models.entity.UserRoleEntity;
import bg.DeveloperGroup.musicdb.models.entity.enums.Genre;
import bg.DeveloperGroup.musicdb.models.entity.enums.UserRole;
import bg.DeveloperGroup.musicdb.repository.AlbumRepository;
import bg.DeveloperGroup.musicdb.repository.ArtistRepository;
import bg.DeveloperGroup.musicdb.repository.LogRepository;
import bg.DeveloperGroup.musicdb.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
//@DirtiesContext -> very slow operations
public class AlbumControllerTest {

    private static final String ALBUM_CONTROLLER_PREFIX = "/albums";
    private long testAlbumId;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private LogRepository logRepository;

    private AlbumTestData testData;

    @BeforeEach
    public void setup() throws Exception {
        testData = new AlbumTestData(albumRepository, userRepository,
                artistRepository, logRepository);
        testData.init();
        testAlbumId = testData.getTestAlbumId();
    }

    @AfterEach
    public void tearDown(){
        testData.cleanUp();

    }


    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                        ALBUM_CONTROLLER_PREFIX + "/details/{id}", testAlbumId
                )).
                andExpect(status().isOk()).
                andExpect(view().name("details")).
                andExpect(model().attributeExists("album"));
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void addAlbum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ALBUM_CONTROLLER_PREFIX + "/add")
                        .param("name", "test album").
                        param("genre", Genre.METAL.name()).
                        param("imageUrl", "http://example.com/image.png").
                        param("videoUrl", "_fKAsvJrFes").
                        param("description", "Description test").
                        param("copies", "123333").
                        param("price", "10").
                        param("releaseDate", "2000-01-01").
                        param("artist", "METALLICA").
                        with(csrf())).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(3, albumRepository.count());
        //todo: may verify the created album properties
    }


}
