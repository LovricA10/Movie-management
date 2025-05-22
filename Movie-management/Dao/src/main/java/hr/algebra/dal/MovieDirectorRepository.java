/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.Director;
import hr.algebra.model.MovieDirector;
import java.util.List;

/**
 *
 * @author Lovric
 */
public interface MovieDirectorRepository {
    int createMovieDirector(MovieDirector movieDirector) throws Exception;
    void deleteMovieDirector(int movieId,int directorId) throws Exception;
    List<Director> selectDirectorsForMovie(int movieId) throws Exception;
    List<Director> selectDirectorsNotInMovie(int movieId) throws Exception;
}
