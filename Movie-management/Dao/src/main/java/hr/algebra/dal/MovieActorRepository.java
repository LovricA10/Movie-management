/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.MovieActor;
import java.util.List;

/**
 *
 * @author Lovric
 */
public interface MovieActorRepository {
    int createMovieActor(MovieActor movieActor) throws Exception;
    void createMovieActors(List<MovieActor> movieActors) throws Exception;
    void deleteMovieActor(int id) throws Exception;
    List<MovieActor> selectMovieActors() throws Exception;
}
