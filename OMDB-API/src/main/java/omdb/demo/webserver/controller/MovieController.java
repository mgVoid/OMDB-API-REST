package omdb.demo.webserver.controller;

import org.springframework.core.env.Environment;
import omdb.demo.webserver.domain.Movie;
import omdb.demo.webserver.domain.Search;
import omdb.demo.webserver.service.MovieClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private MovieClientService movieClientService;
    private Environment env;

    @Autowired
    public MovieController(MovieClientService movieClientService, Environment env) {
        this.movieClientService = movieClientService;
        this.env = env;
    }

    @GetMapping("/movies/title/{title}")
    public Mono<Movie> getMovieByTitle(@PathVariable String title) {
        String apiKey = env.getProperty("app.api.key");
        return movieClientService.searchMovieByTitle(apiKey, title);
    }

    @GetMapping("/movies/id/{imdbId}")
    public Mono<Movie> getMovieById(@PathVariable String imdbId) {
        return movieClientService.searchMovieById(env.getProperty("app.api.key"), imdbId);
    }

    @GetMapping("/movies/keyword/{word}")
    public Mono<java.util.List<Search>> searchMoviesByKeyword(@PathVariable String word) {
        return movieClientService.searchMoviesByKeyword(env.getProperty("app.api.key"), word);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        logger.error("Error from WebClient - Status {}, Body {}", ex.getRawStatusCode(), ex.getResponseBodyAsString(),
                ex);
        return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
    }
}
