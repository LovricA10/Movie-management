/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.MovieGenre;
import java.util.List;

/**
 *
 * @author Lovric
 */
public interface MovieGenreRepository {
    int createMovieGenre(MovieGenre movieGenre) throws Exception;
    void createMovieGenres(List<MovieGenre> movieGenres) throws Exception;
    void deleteMovieGenre(int id) throws Exception;
    List<MovieGenre> selectMovieGenres() throws Exception;
}
