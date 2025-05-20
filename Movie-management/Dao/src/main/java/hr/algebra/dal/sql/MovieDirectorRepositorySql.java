/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.MovieDirectorRepository;
import hr.algebra.model.MovieDirector;
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
public class MovieDirectorRepositorySql implements MovieDirectorRepository{
    private static final String ID_MOVIE_DIRECTOR = "IDMovieDirector";
    private static final String MOVIE_ID = "MovieID";
    private static final String DIRECTOR_ID = "DirectorID";

    private static final String CREATE_MOVIE_DIRECTOR = "{ CALL createMovieDirector(?, ?, ?) }";
    private static final String DELETE_MOVIE_DIRECTOR = "{ CALL deleteMovieDirector(?) }";
    private static final String SELECT_MOVIE_DIRECTORS = "{ CALL selectMovieDirectors }";
    private static final String DELETE_MOVIE_DIRECTOR_BY_MOVIE_AND_DIRECTOR = "{ CALL deleteMovieDirectorByMovieAndDirector(?, ?) }";

    @Override
    public int createMovieDirector(MovieDirector movieDirector) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(CREATE_MOVIE_DIRECTOR)) {

            stmt.setInt(MOVIE_ID, movieDirector.getMovieId());
            stmt.setInt(DIRECTOR_ID, movieDirector.getDirectorId());
            stmt.registerOutParameter(ID_MOVIE_DIRECTOR, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_MOVIE_DIRECTOR);
        }
    }

    @Override
    public void createMovieDirectors(List<MovieDirector> movieDirectors) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(CREATE_MOVIE_DIRECTOR)) {

            for (MovieDirector movieDirector : movieDirectors) {
                stmt.setInt(MOVIE_ID, movieDirector.getMovieId());
                stmt.setInt(DIRECTOR_ID, movieDirector.getDirectorId());
                stmt.registerOutParameter(ID_MOVIE_DIRECTOR, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void deleteMovieDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(DELETE_MOVIE_DIRECTOR)) {

            stmt.setInt(ID_MOVIE_DIRECTOR, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<MovieDirector> selectMovieDirectors() throws Exception {
         List<MovieDirector> movieDirectors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(SELECT_MOVIE_DIRECTORS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movieDirectors.add(new MovieDirector(
                        rs.getInt(ID_MOVIE_DIRECTOR),
                        rs.getInt(MOVIE_ID),
                        rs.getInt(DIRECTOR_ID)
                ));
            }
        }
        return movieDirectors;
    }

    @Override
    public void deleteByMovieAndDirector(int movieId, int directorId) throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
            try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE_DIRECTOR_BY_MOVIE_AND_DIRECTOR)) {

                stmt.setInt(MOVIE_ID, movieId);
                stmt.setInt(DIRECTOR_ID, directorId);
                stmt.executeUpdate();
    }
    }
}
