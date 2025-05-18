/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.MovieUserRepository;
import hr.algebra.model.MovieUser;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Lovric
 */
public class MovieUserRepositorySql implements MovieUserRepository{
    
    private static final String ID_MOVIE_USER = "IDMovieUser";
    private static final String MOVIE_ID = "MovieID";
    private static final String USER_ID = "UserID";
    private static final String RATING = "Rating";
    private static final String COMMENT = "Comment";
    private static final String FAVORITE = "Favorite";

    private static final String CREATE_MOVIE_USER = "{ CALL createMovieUser(?, ?, ?, ?, ?, ?) }";
    private static final String UPDATE_MOVIE_USER = "{ CALL updateMovieUser(?, ?, ?, ?, ?, ?) }";
    private static final String DELETE_MOVIE_USER = "{ CALL deleteMovieUser(?) }";
    private static final String SELECT_MOVIE_USER = "{ CALL selectMovieUser(?) }";
    private static final String SELECT_MOVIE_USERS = "{ CALL selectMovieUsers }";

    @Override
    public int createMovieUser(MovieUser movieUser) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(CREATE_MOVIE_USER)) {

            stmt.setInt(MOVIE_ID, movieUser.getMovieId());
            stmt.setInt(USER_ID, movieUser.getUserId());
            stmt.setInt(RATING, movieUser.getRating());
            stmt.setString(COMMENT, movieUser.getComment());
            stmt.setBoolean(FAVORITE, movieUser.isFavorite());
            stmt.registerOutParameter(ID_MOVIE_USER, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_MOVIE_USER);
        }
    }

    @Override
    public void createMovieUsers(List<MovieUser> movieUsers) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(CREATE_MOVIE_USER)) {

            for (MovieUser movieUser : movieUsers) {
                stmt.setInt(MOVIE_ID, movieUser.getMovieId());
                stmt.setInt(USER_ID, movieUser.getUserId());
                stmt.setInt(RATING, movieUser.getRating());
                stmt.setString(COMMENT, movieUser.getComment());
                stmt.setBoolean(FAVORITE, movieUser.isFavorite());
                stmt.registerOutParameter(ID_MOVIE_USER, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void updateMovieUser(int id, MovieUser data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(UPDATE_MOVIE_USER)) {

            stmt.setInt(MOVIE_ID, data.getMovieId());
            stmt.setInt(USER_ID, data.getUserId());
            stmt.setInt(RATING, data.getRating());
            stmt.setString(COMMENT, data.getComment());
            stmt.setBoolean(FAVORITE, data.isFavorite());
            stmt.setInt(ID_MOVIE_USER, id);

            stmt.executeUpdate();
        }
    }   

    @Override
    public void deleteMovieUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(DELETE_MOVIE_USER)) {

            stmt.setInt(ID_MOVIE_USER, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<MovieUser> selectMovieUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(SELECT_MOVIE_USER)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new MovieUser(
                            rs.getInt(ID_MOVIE_USER),
                            rs.getInt(MOVIE_ID),
                            rs.getInt(USER_ID),
                            rs.getInt(RATING),
                            rs.getString(COMMENT),
                            rs.getBoolean(FAVORITE)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<MovieUser> selectMovieUsers() throws Exception {
        List<MovieUser> movieUsers = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(SELECT_MOVIE_USERS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movieUsers.add(new MovieUser(
                        rs.getInt(ID_MOVIE_USER),
                        rs.getInt(MOVIE_ID),
                        rs.getInt(USER_ID),
                        rs.getInt(RATING),
                        rs.getString(COMMENT),
                        rs.getBoolean(FAVORITE)
                ));
            }
        }
        return movieUsers;
    }
}
