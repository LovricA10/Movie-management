/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.MovieUser;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Lovric
 */
public interface MovieUserRepository {

    int createMovieUser(MovieUser movieUser) throws Exception;

    void createMovieUsers(List<MovieUser> movieUsers) throws Exception;

    void updateMovieUser(int id, MovieUser data) throws Exception;

    void deleteMovieUser(int id) throws Exception;

    Optional<MovieUser> selectMovieUser(int id) throws Exception;

    List<MovieUser> selectMovieUsers() throws Exception;
}
