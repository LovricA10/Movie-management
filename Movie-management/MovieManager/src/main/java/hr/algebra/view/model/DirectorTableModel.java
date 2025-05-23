/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view.model;

import hr.algebra.model.Director;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lovric
 */
public class DirectorTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {
        "ID",
        "Name",
        "Last Name",
        "Date of Birth",
        "Picture path"
    };

    private List<Director> directors;

    public DirectorTableModel(List<Director> directors) {
        this.directors = directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return directors.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Director director = directors.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                director.getId();
            case 1 ->
                director.getName();
            case 2 ->
                director.getLastName();
            case 3 ->
                director.getDateBirth().format(Director.DATE_FORMATTER);
            case 4 ->
                director.getPicturePath();
            default ->
                throw new RuntimeException("No such column");
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 ->
                Integer.class;
            case 3 ->
                String.class; //  date formatted to String
            default ->
                String.class;
        };
    }
}
