package omdb.demo.webserver.service;

import omdb.demo.webserver.domain.Movie;
import omdb.demo.webserver.domain.Search;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MovieClientServiceImpl implements MovieClientService {
    private static final String OMDB_MIME_TYPE = "application/json";
    private static final String OMDB_API_BASE_URL = "http://omdbapi.com";
    private static final String USER_AGENT = "Spring 5 WebClient";

    private final WebClient webClient;

    public MovieClientServiceImpl() {
        this.webClient = WebClient.builder().baseUrl(OMDB_API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, OMDB_MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT).build();
    }

    @Override
    public Mono<Movie> searchMovieByTitle(String apiKey, String title) {
        return webClient.post().uri("/?apikey=" + apiKey + "&t=" + title).retrieve().bodyToMono(Movie.class);
    }

    @Override
    public Mono<Movie> searchMovieById(String apiKey, String imdbId) {
        return webClient.post().uri("/?apikey=" + apiKey + "&i=" + imdbId).retrieve().bodyToMono(Movie.class);
    }

    @Override
    public Mono<java.util.List<Search>> searchMoviesByKeyword(String apiKey, String word) {
        return webClient.post().uri("/?apikey=" + apiKey + "&s=" + word).retrieve().bodyToFlux(Search.class)
                .collectList();

    }

}
