/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.MovieActorRepository;
import hr.algebra.model.MovieActor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Lovric
 */
public class MovieActorRepositorySql implements MovieActorRepository{
    private static final String ID_MOVIE_ACTOR = "IDMovieActor";
    private static final String MOVIE_ID = "MovieID";
    private static final String ACTOR_ID = "ActorID";

    private static final String CREATE_MOVIE_ACTOR = "{ CALL createMovieActor(?, ?, ?) }";
    private static final String DELETE_MOVIE_ACTOR = "{ CALL deleteMovieActor(?) }";
    private static final String SELECT_MOVIE_ACTORS = "{ CALL selectMovieActors }";

    @Override
    public int createMovieActor(MovieActor movieActor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(CREATE_MOVIE_ACTOR)) {

            stmt.setInt(MOVIE_ID, movieActor.getMovieId());
            stmt.setInt(ACTOR_ID, movieActor.getActorId());
            stmt.registerOutParameter(ID_MOVIE_ACTOR, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_MOVIE_ACTOR);
        }
    }

    @Override
    public void createMovieActors(List<MovieActor> movieActors) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(CREATE_MOVIE_ACTOR)) {

            for (MovieActor movieActor : movieActors) {
                stmt.setInt(MOVIE_ID, movieActor.getMovieId());
                stmt.setInt(ACTOR_ID, movieActor.getActorId());
                stmt.registerOutParameter(ID_MOVIE_ACTOR, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void deleteMovieActor(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(DELETE_MOVIE_ACTOR)) {

            stmt.setInt(ID_MOVIE_ACTOR, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<MovieActor> selectMovieActors() throws Exception {
         List<MovieActor> movieActors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(SELECT_MOVIE_ACTORS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movieActors.add(new MovieActor(
                        rs.getInt(ID_MOVIE_ACTOR),
                        rs.getInt(MOVIE_ID),
                        rs.getInt(ACTOR_ID)
                ));
            }
        }
        return movieActors;
    }
}
