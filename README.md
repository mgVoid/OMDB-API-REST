# OMDB-API-REST

To run the repository as a REST API, type the following command after cloning:
```
mvn spring-boot:run
```
Or build the JAR file and run it with the following commands:
```
mvn clean package
```
```
java -jar -Dserver.port=[desiredPort] target/webclient-omdb-api-0.0.1-SNAPSHOT.jar
```
The REST endpoints will be available at: http://localhost:8080 (unless you've specified a different port).
```
Enter your http://www.omdbapi.com API key @ src/main/resources/aplication.properties
```
Search options:
```
http://localhost:8080/movies/id/"A valid IMDb ID (e.g. tt1285016)"
```
To GET a JSON response with the /Title, Year, IMDB rating, Genre, Plot, Actors\ of a movie.
```
http://localhost:8080/movies/title/"Movie title to search for (e.g. Interstellar)"
```
To GET a JSON response with the /Title, Year, IMDB rating, Genre, Plot, Actors\ of a movie.
```
http://localhost:8080/movies/keyword/"A keyword/movie title to search for (e.g future)" 
```
To GET a JSON response with up to 10 movies, incl. /ResultCount, Title, Year, imdbID, Type, Poster\
Test cases can be found ``` @ src/test/java/omdb/demo/webserver/service```
