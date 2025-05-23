/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.MovieActorRepository;
import hr.algebra.model.Actor;
import hr.algebra.model.MovieActor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Lovric
 */
public class MovieActorRepositorySql implements MovieActorRepository {

    private static final String ID_MOVIE_ACTOR = "IDMovieActor";
    private static final String MOVIE_ID = "MovieID";
    private static final String ACTOR_ID = "ActorID";

    private static final String PICTURE_PATH = "PicturePath";
    private static final String DATE_BIRTH = "DateBirth";
    private static final String LAST_NAME = "LastName";
    private static final String NAME = "Name";
    private static final String ID_ACTOR = "IDActor";

    private static final String CREATE_MOVIE_ACTOR = "{ CALL createMovieActor(?, ?, ?) }";
    private static final String DELETE_MOVIE_ACTOR_BY_MOVIE_ACTOR = "{ CALL deleteMovieActorByMovieActor(?, ?) }";
    private static final String SELECT_ACTORS_FOR_MOVIE = "{ CALL selectActorsForMovie(?) }";
    private static final String SELECT_ACTORS_NOT_IN_MOVIE = "{ CALL selectActorsNotInMovie(?) }";

    @Override
    public int createMovieActor(MovieActor movieActor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE_ACTOR)) {

            stmt.setInt(MOVIE_ID, movieActor.getMovieId());
            stmt.setInt(ACTOR_ID, movieActor.getActorId());
            stmt.registerOutParameter(ID_MOVIE_ACTOR, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_MOVIE_ACTOR);
        }
    }

    @Override
    public void deleteMovieActor(int movieId, int actorId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MOVIE_ACTOR_BY_MOVIE_ACTOR)) {

            stmt.setInt(MOVIE_ID, movieId);
            stmt.setInt(ACTOR_ID, actorId);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Actor> selectActorsForMovie(int movieId) throws Exception {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ACTORS_FOR_MOVIE)) {
            stmt.setInt(MOVIE_ID, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    actors.add(new Actor(
                            rs.getInt(ID_ACTOR),
                            rs.getString(NAME),
                            rs.getString(LAST_NAME),
                            LocalDate.parse(rs.getString(DATE_BIRTH), Actor.DATE_FORMATTER),
                            rs.getString(PICTURE_PATH)
                    ));
                }
            }
        }
        return actors;
    }

    @Override
    public List<Actor> selectActorsNotInMovie(int movieId) throws Exception {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ACTORS_NOT_IN_MOVIE)) {
            stmt.setInt(MOVIE_ID, movieId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    actors.add(new Actor(
                            rs.getInt(ID_ACTOR),
                            rs.getString(NAME),
                            rs.getString(LAST_NAME),
                            LocalDate.parse(rs.getString(DATE_BIRTH), Actor.DATE_FORMATTER),
                            rs.getString(PICTURE_PATH)
                    ));
                }
            }
        }
        return actors;
    }
}
