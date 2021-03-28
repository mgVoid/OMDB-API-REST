package omdb.demo.webserver.service;

import reactor.core.publisher.Mono;
import omdb.demo.webserver.domain.Movie;
import omdb.demo.webserver.domain.Search;

public interface MovieClientService {

    public Mono<Movie> searchMovieByTitle(String apiKey, String title);

    public Mono<Movie> searchMovieById(String apiKey, String imdbId);

    public Mono<java.util.List<Search>> searchMoviesByKeyword(String apiKey, String word);
}