package bg.DeveloperGroup.musicdb.web;

import bg.DeveloperGroup.musicdb.models.entity.enums.Genre;
import bg.DeveloperGroup.musicdb.repository.AlbumRepository;
import bg.DeveloperGroup.musicdb.repository.ArtistRepository;
import bg.DeveloperGroup.musicdb.repository.LogRepository;
import bg.DeveloperGroup.musicdb.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AlbumRestControllerTest {

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
    public void setUp(){
        testData = new AlbumTestData(albumRepository, userRepository,
                artistRepository, logRepository);
        testData.init();

    }

    @AfterEach
    public void tearDown(){
        testData.cleanUp();
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void testFetchAlbums() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/albums/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("JUSTICE FOR ALL"))
                .andExpect(jsonPath("[0].imageUrl").value("https://upload.wikimedia.org/wikipedia/en/b/bd/Metallica_-_...And_Justice_for_All_cover.jpg"))
                .andExpect(jsonPath("[0].videoUrl").value("_fKAsvJrFes"))
                .andExpect(jsonPath("[0].description").value("Sample description"))
                .andExpect(jsonPath("[0].copies").value("11"))
                .andExpect(jsonPath("[0].price").value(10))
//                .andExpect(jsonPath("[0].LocalDate").value(LocalDate.of(1988, 3, 3).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .andExpect(jsonPath("[0].genre").value(Genre.METAL.name()))
                .andExpect(jsonPath("[1].name").value("MASTER OF PUPPETS"))
                .andExpect(jsonPath("[1].imageUrl").value("https://upload.wikimedia.org/wikipedia/en/b/bd/Metallica_-_...And_Justice_for_All_cover.jpg"))
                .andExpect(jsonPath("[1].videoUrl").value("_fKAsvJrFes"))
                .andExpect(jsonPath("[1].description").value("Sample description"))
                .andExpect(jsonPath("[1].copies").value("111"))
                .andExpect(jsonPath("[1].price").value(10));


    }
}
