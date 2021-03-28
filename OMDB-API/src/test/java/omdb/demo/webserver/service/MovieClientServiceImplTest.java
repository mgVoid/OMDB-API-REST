package omdb.demo.webserver.service;

import org.junit.Before;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MovieClientServiceImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        webTestClient = webTestClient.mutate().build();
    }

    @Test
    public void testGetMovieById() {
        webTestClient.get().uri("/movies/id/{imdbId}", "tt3896198").accept(MediaType.APPLICATION_JSON).exchange()
                .expectStatus().isOk().expectBody()
                .consumeWith(response -> Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void testGetMovieByTitle() {
        webTestClient.get().uri("/movies/title/{title}", "Inception").exchange().expectStatus().isOk().expectBody()
                .consumeWith(response -> Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void testGetMoviesByKeyword() {
        webTestClient.get().uri("/movies/keyword/{word}", "999").exchange().expectStatus().isOk().expectBody()
                .consumeWith(response -> Assertions.assertThat(response.getResponseBody()).isNotNull());
    }
}
