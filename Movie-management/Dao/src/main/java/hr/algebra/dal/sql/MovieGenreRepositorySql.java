/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.MovieGenreRepository;
import hr.algebra.model.Actor;
import hr.algebra.model.Genre;
import hr.algebra.model.MovieGenre;
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
public class MovieGenreRepositorySql implements MovieGenreRepository{
    
    private static final String ID_MOVIE_GENRE = "IDMovieGenre";
    private static final String MOVIE_ID = "MovieID";
    private static final String GENRE_ID = "GenreID";
    
    private static final String ID_GENRE = "IDGenre";
    private static final String GENRE_NAME = "GenreName";

    private static final String CREATE_MOVIE_GENRE = "{ CALL createMovieGenre(?, ?, ?) }";
    private static final String DELETE_MOVIE_GENRE_BY_MOVIE_GENRE = "{ CALL deleteMovieGenreByMovieAndGenre(?, ?) }";
    private static final String SELECT_GENRES_FOR_MOVIE = "{ CALL selectGenresForMovie(?) }";
    private static final String SELECT_GENRES_NOT_IN_MOVIE = "{ CALL selectGenresNotInMovie(?) }";

    @Override
    public int createMovieGenre(MovieGenre movieGenre) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(CREATE_MOVIE_GENRE)) {

            stmt.setInt(MOVIE_ID, movieGenre.getMovieId());
            stmt.setInt(GENRE_ID, movieGenre.getGenreId());
            stmt.registerOutParameter(ID_MOVIE_GENRE, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_MOVIE_GENRE);
        }
    }

    @Override
    public void deleteMovieGenre(int movieId, int genreId ) throws Exception {
             DataSource dataSource = DataSourceSingleton.getInstance();
             try (Connection con = dataSource.getConnection();
                 CallableStatement stmt = con.prepareCall(DELETE_MOVIE_GENRE_BY_MOVIE_GENRE)) {

                    stmt.setInt(MOVIE_ID, movieId);
                    stmt.setInt(GENRE_ID, genreId);

                    stmt.executeUpdate();
    }
    }

    @Override
    public List<Genre> selectGenresForMovie(int movieId) throws Exception {
           List<Genre> genres = new ArrayList<>();
            DataSource dataSource = DataSourceSingleton.getInstance();
            try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_GENRES_FOR_MOVIE)) {
                stmt.setInt(MOVIE_ID, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                genres.add(new Genre(
                    rs.getInt(ID_GENRE),
                    rs.getString(GENRE_NAME)
                ));
            }
        }
    }
    return genres;
    }

    @Override
    public List<Genre> selectGenresNotInMovie(int movieId) throws Exception {
         List<Genre> genres = new ArrayList<>();
            DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(SELECT_GENRES_NOT_IN_MOVIE)) {
            stmt.setInt(MOVIE_ID, movieId);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                genres.add(new Genre(
                    rs.getInt(ID_GENRE),
                    rs.getString(GENRE_NAME)
                ));
            }
        }
    }
    return genres;
    }

    
}
