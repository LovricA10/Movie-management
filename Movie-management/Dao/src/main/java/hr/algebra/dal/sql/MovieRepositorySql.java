/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.MovieRepository;
import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import hr.algebra.dal.UserRepository;
import hr.algebra.model.Movie;
import java.time.LocalDate;

public class MovieRepositorySql implements MovieRepository {

    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String DURATION = "Duration";
    private static final String START_DATE = "StartDate";
    private static final String PICTURE_PATH = "PicturePath";

   private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL selectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL selectMovies }";

    @Override
    public int createMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
            try(Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
                stmt.setString(TITLE, movie.getTitle());
                stmt.setInt(DURATION, movie.getDuration());
                stmt.setString(START_DATE, movie.getStartDate().format(Movie.DATE_FORMATTER));
                stmt.setString(PICTURE_PATH, movie.getPicturePath());
                
                stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);
                stmt.executeUpdate();
                return stmt.getInt(ID_MOVIE);
            }
    }

    @Override
    public void createMovies(List<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
            try(Connection con = dataSource.getConnection();
                    CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
                for (Movie movie : movies) {
                     stmt.setString(TITLE, movie.getTitle());
                    stmt.setInt(DURATION, movie.getDuration());
                    stmt.setString(START_DATE, movie.getStartDate().format(Movie.DATE_FORMATTER));
                    stmt.setString(PICTURE_PATH, movie.getPicturePath());

                    stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);

                    stmt.executeUpdate();
                }
    }
 }
    @Override
    public void updateMovie(int id, Movie movie) throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
            try(Connection con = dataSource.getConnection();
                    CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {
                    stmt.setString(TITLE, movie.getTitle());
                    stmt.setInt(DURATION, movie.getDuration());
                    stmt.setString(START_DATE, movie.getStartDate().format(Movie.DATE_FORMATTER));
                    stmt.setString(PICTURE_PATH, movie.getPicturePath());
                    stmt.setInt(ID_MOVIE, id);
                    
                    stmt.executeUpdate();
            }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
       DataSource dataSource = DataSourceSingleton.getInstance();
            try(Connection con = dataSource.getConnection();
                    CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {  
                    stmt.setInt(ID_MOVIE, id);
                    stmt.executeUpdate();
            }
    }

    @Override
    public Optional<Movie> selectMovie(int id) throws Exception {
      DataSource dataSource = DataSourceSingleton.getInstance();
            try(Connection con = dataSource.getConnection();
                    CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {  
                    stmt.setInt(ID_MOVIE, id);
               try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                   return Optional.of(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        rs.getInt(DURATION),
                        LocalDate.parse(rs.getString(START_DATE), Movie.DATE_FORMATTER),
                        rs.getString(PICTURE_PATH)
                ));
            }
        }
    } 
            return Optional.empty();
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
           List<Movie> movies = new ArrayList<>();
              DataSource dataSource = DataSourceSingleton.getInstance();
            try(Connection con = dataSource.getConnection();
                    CallableStatement stmt = con.prepareCall(SELECT_MOVIES)) {  
               try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                   movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        rs.getInt(DURATION),
                        LocalDate.parse(rs.getString(START_DATE), Movie.DATE_FORMATTER),
                        rs.getString(PICTURE_PATH)
                ));
           }
            return movies;
        }
    }
  }
}
   
     
