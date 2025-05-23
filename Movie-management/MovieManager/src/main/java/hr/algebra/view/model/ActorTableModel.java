/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view.model;

import hr.algebra.model.Actor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lovric
 */
public class ActorTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {
        "ID",
        "Name",
        "Last Name",
        "Date of Birth",
        "Picture Path"
    };

    private List<Actor> actors;

    public ActorTableModel(List<Actor> actors) {
        this.actors = actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return actors.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Actor actor = actors.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                actor.getId();
            case 1 ->
                actor.getName();
            case 2 ->
                actor.getLastName();
            case 3 ->
                actor.getDateBirth().format(Actor.DATE_FORMATTER);
            case 4 ->
                actor.getPicturePath();
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
                String.class; // date formatted as string
            default ->
                String.class;
        };
    }

}
