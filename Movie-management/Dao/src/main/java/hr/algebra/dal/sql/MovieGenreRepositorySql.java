/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.MovieGenreRepository;
import hr.algebra.model.MovieGenre;
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
public class MovieGenreRepositorySql implements MovieGenreRepository{
    private static final String ID_MOVIE_GENRE = "IDMovieGenre";
    private static final String MOVIE_ID = "MovieID";
    private static final String GENRE_ID = "GenreID";

    private static final String CREATE_MOVIE_GENRE = "{ CALL createMovieGenre(?, ?, ?) }";
    private static final String DELETE_MOVIE_GENRE = "{ CALL deleteMovieGenre(?) }";
    private static final String SELECT_MOVIE_GENRES = "{ CALL selectMovieGenres }";

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
    public void createMovieGenres(List<MovieGenre> movieGenres) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(CREATE_MOVIE_GENRE)) {

            for (MovieGenre movieGenre : movieGenres) {
                stmt.setInt(MOVIE_ID, movieGenre.getMovieId());
                stmt.setInt(GENRE_ID, movieGenre.getGenreId());
                stmt.registerOutParameter(ID_MOVIE_GENRE, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void deleteMovieGenre(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(DELETE_MOVIE_GENRE)) {

            stmt.setInt(ID_MOVIE_GENRE, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<MovieGenre> selectMovieGenres() throws Exception {
        List<MovieGenre> movieGenres = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(SELECT_MOVIE_GENRES);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movieGenres.add(new MovieGenre(
                        rs.getInt(ID_MOVIE_GENRE),
                        rs.getInt(MOVIE_ID),
                        rs.getInt(GENRE_ID)
                ));
            }
        }
        return movieGenres;
    }
}
