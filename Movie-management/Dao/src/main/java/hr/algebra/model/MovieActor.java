/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author Lovric
 */
public final class MovieActor {

    private int id;
    private int movieId;
    private int actorId;

    public MovieActor() {
    }

    public MovieActor(int id, int movieId, int actorId) {
        this.id = id;
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public MovieActor(int movieId, int actorId) {
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

}
