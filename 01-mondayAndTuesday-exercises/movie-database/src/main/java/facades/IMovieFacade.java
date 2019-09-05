/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.MovieDTO;
import entities.Movie;
import java.util.List;

/**
 *
 * @author ndupo
 */
public interface IMovieFacade {

    List<MovieDTO> getAllMoviesDTO();

    MovieDTO getMovieDTOById(long id);
    
    Movie makeMovie(Movie movie);

    long getMovieCount();

    MovieDTO getMovieDTOByName(String name);

    void populateMovies();
    
}
