/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.Genre;
import hr.algebra.model.MovieGenre;
import java.util.List;

/**
 *
 * @author Lovric
 */
public interface MovieGenreRepository {
    int createMovieGenre(MovieGenre movieGenre) throws Exception;
    void deleteMovieGenre(int movieId,int genreId) throws Exception;
    List<Genre> selectGenresForMovie(int movieId) throws Exception;
    List<Genre> selectGenresNotInMovie(int movieId) throws Exception;
    
   
}
