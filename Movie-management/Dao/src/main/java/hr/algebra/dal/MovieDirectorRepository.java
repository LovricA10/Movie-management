/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.MovieDirector;
import java.util.List;

/**
 *
 * @author Lovric
 */
public interface MovieDirectorRepository {
    int createMovieDirector(MovieDirector movieDirector) throws Exception;
    void createMovieDirectors(List<MovieDirector> movieDirectors) throws Exception;
    void deleteMovieDirector(int id) throws Exception;
    List<MovieDirector> selectMovieDirectors() throws Exception;
}
