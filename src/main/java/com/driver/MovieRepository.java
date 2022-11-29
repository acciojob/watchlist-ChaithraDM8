package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Repository
public class MovieRepository {

    HashMap<String,Movie> Mdatabase=new HashMap<>();
    HashMap<String,Director> Ddatabase=new HashMap<>();
    HashMap<String,List<String>> MDdatabase=new HashMap<>();

    List<Movie> listofMovies;
    List<Director> listofDirector= new ArrayList<>();

    public void addMovie(Movie movie){
        Mdatabase.put(movie.getName(),movie);

    }

    public void addDirector(Director director) {
        Ddatabase.put(director.getName(),director);
        for(Director directors:Ddatabase.values()){
            listofDirector.add(directors);
        }
    }

//    public boolean getDirector(String name) {
//        if(Ddatabase.containsKey(name))
//            return true;
//        return false;
//    }
//
//    public int getNumberofMovies(String name) {
//            Director noOfMovies=Ddatabase.get(name);
//        return noOfMovies.getNumberOfMovies();
//    }

    public Movie getMovieByName(String name) {
        return Mdatabase.get(name);
    }

    public Director getDirectorByName(String name) {
        return Ddatabase.get(name);
    }

    public List<Movie> getAllMovies() {
        listofMovies= new ArrayList<>();
        for(Movie movies:Mdatabase.values()){
            listofMovies.add(movies);
        }
        return listofMovies;

    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        List<String> list = new ArrayList<>();
        if (MDdatabase.containsKey(directorName)) {
            list = MDdatabase.get(directorName);
            list.add(movieName);
            MDdatabase.put(directorName, list);
        } else {
            list.add(movieName);
            MDdatabase.put(directorName, list);
        }
    }
    public List<String> getMoviesByDirectorName(String directorName) {
        return MDdatabase.get(directorName);
    }

    public void deleteDirectorByName(String directorName) {
        List<String> list = new ArrayList<>();
        list = MDdatabase.get(directorName);

        MDdatabase.remove(directorName);
        Ddatabase.remove(directorName);

        for(String movieName : list){
            Mdatabase.remove(movieName);
        }


    }

    public void deleteAllDirectors() {
        for(String directorName:MDdatabase.keySet()){
            deleteDirectorByName(directorName);
        }
    }
}
