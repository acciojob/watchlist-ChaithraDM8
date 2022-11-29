package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Repository
public class MovieRepository {

    HashMap<String,Movie> movieDatabase =new HashMap<>();
    HashMap<String,Director> directorDatabase =new HashMap<>();
    HashMap<String,List<String>> movieDirectorDatabase =new HashMap<>();

    List<Movie> listofMovies;
    public void addMovie(Movie movie){
        movieDatabase.put(movie.getName(),movie);

    }

    public void addDirector(Director director) {
        directorDatabase.put(director.getName(),director);
    }

    public Movie getMovieByName(String name) {
        return movieDatabase.get(name);
    }

    public Director getDirectorByName(String name) {
        return directorDatabase.get(name);
    }

    public List<Movie> getAllMovies() {
        listofMovies= new ArrayList<>();
        for(Movie movies: movieDatabase.values()){
            listofMovies.add(movies);
        }
        return listofMovies;

    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        List<String> list = new ArrayList<>();
        if (movieDirectorDatabase.containsKey(directorName)) {
            list = movieDirectorDatabase.get(directorName);
            list.add(movieName);
            movieDirectorDatabase.put(directorName, list);
        } else {
            list.add(movieName);
            movieDirectorDatabase.put(directorName, list);
        }
    }
    public List<String> getMoviesByDirectorName(String directorName) {
        return movieDirectorDatabase.get(directorName);
    }

    public void deleteDirectorByName(String directorName) {
        List<String> list = new ArrayList<>();
        list = movieDirectorDatabase.get(directorName);

        movieDirectorDatabase.remove(directorName);
        directorDatabase.remove(directorName);

        for (String movieName : list) {
            movieDatabase.remove(movieName);
        }
    }
    public void deleteAllDirectors() {
        for(String directorName: movieDirectorDatabase.keySet()){
            deleteDirectorByName(directorName);
        }
    }
}
