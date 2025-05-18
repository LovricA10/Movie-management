/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view.model;

import hr.algebra.model.Genre;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lovric
 */
public class GenreTableModel extends AbstractTableModel{

    private static final String[] COLUMN_NAMES = {
       "ID",
       "Genre Name"
    };

    private List<Genre> genres;

    public GenreTableModel(List<Genre> genres) {
        this.genres = genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
        fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return genres.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Genre genre = genres.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> genre.getId();
            case 1 -> genre.getGenreName();
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
            case 0 -> Integer.class;
            case 1 -> String.class;
            default -> Object.class;
        };
    }
}
