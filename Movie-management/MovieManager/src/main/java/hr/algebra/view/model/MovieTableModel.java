/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.view.model;

import hr.algebra.model.Movie;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dnlbe
 */
public class MovieTableModel extends AbstractTableModel{
    
    private static final String[] COLUMN_NAMES = {
        "ID",
        "Title",
        "Link",
        "Description",
        "Start Date",
        "Picture Path"
    };
    
    private List<Movie> movies;

     public MovieTableModel(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Movie movie = movies.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> movie.getId();
            case 1 -> movie.getTitle();
            case 2 -> movie.getLink();
            case 3 -> movie.getDescription();
            case 4 -> movie.getStartDate().format(Movie.DATE_FORMATTER);
            case 5 -> movie.getPicturePath();
            default -> throw new RuntimeException("No such column");
        };
        }
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
      return switch (columnIndex) {
        case 0, 2 -> Integer.class;
        default -> String.class;
    };
      
      /* public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
        }
        return super.getColumnClass(columnIndex);*/
  }
}
