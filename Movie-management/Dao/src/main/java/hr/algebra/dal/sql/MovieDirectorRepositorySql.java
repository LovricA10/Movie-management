/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.MovieDirectorRepository;
import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import hr.algebra.model.MovieDirector;
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
public class MovieDirectorRepositorySql implements MovieDirectorRepository {

    private static final String ID_MOVIE_DIRECTOR = "IDMovieDirector";
    private static final String MOVIE_ID = "MovieID";
    private static final String DIRECTOR_ID = "DirectorID";

    private static final String PICTURE_PATH = "PicturePath";
    private static final String DATE_BIRTH = "DateBirth";
    private static final String LAST_NAME = "LastName";
    private static final String NAME = "Name";
    private static final String ID_DIRECTOR = "IDDirector";

    private static final String CREATE_MOVIE_DIRECTOR = "{ CALL createMovieDirector(?, ?, ?) }";
    private static final String DELETE_MOVIE_DIRECTOR_BY_MOVIE_DIRECTOR = "{ CALL deleteMovieDirectorByMovieAndDirector(?, ?) }";
    private static final String SELECT_DIRECTORS_FOR_MOVIE = "{ CALL selectDirectorsForMovie(?) }";
    private static final String SELECT_DIRECTORS_NOT_IN_MOVIE = "{ CALL selectDirectorsNotInMovie(?) }";

    @Override
    public int createMovieDirector(MovieDirector movieDirector) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE_DIRECTOR)) {

            stmt.setInt(MOVIE_ID, movieDirector.getMovieId());
            stmt.setInt(DIRECTOR_ID, movieDirector.getDirectorId());
            stmt.registerOutParameter(ID_MOVIE_DIRECTOR, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_MOVIE_DIRECTOR);
        }
    }

    @Override
    public void deleteMovieDirector(int movieId, int directorId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MOVIE_DIRECTOR_BY_MOVIE_DIRECTOR)) {

            stmt.setInt(MOVIE_ID, movieId);
            stmt.setInt(DIRECTOR_ID, directorId);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Director> selectDirectorsForMovie(int movieId) throws Exception {
        List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS_FOR_MOVIE)) {
            stmt.setInt(MOVIE_ID, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    directors.add(new Director(
                            rs.getInt(ID_DIRECTOR),
                            rs.getString(NAME),
                            rs.getString(LAST_NAME),
                            LocalDate.parse(rs.getString(DATE_BIRTH), Actor.DATE_FORMATTER),
                            rs.getString(PICTURE_PATH)
                    ));
                }
            }
        }
        return directors;
    }

    @Override
    public List<Director> selectDirectorsNotInMovie(int movieId) throws Exception {
        List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS_NOT_IN_MOVIE)) {
            stmt.setInt(MOVIE_ID, movieId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    directors.add(new Director(
                            rs.getInt(ID_DIRECTOR),
                            rs.getString(NAME),
                            rs.getString(LAST_NAME),
                            LocalDate.parse(rs.getString(DATE_BIRTH), Actor.DATE_FORMATTER),
                            rs.getString(PICTURE_PATH)
                    ));
                }
            }
        }
        return directors;
    }
}
