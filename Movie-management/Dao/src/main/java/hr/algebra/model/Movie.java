/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Lovric
 */
public final class Movie {
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private int id;
    private String title;
    private int duration;
    private LocalDateTime startDate;
    private String picturePath;
    
    
    public Movie(){
        
    }

    public Movie(int id, String title, int duration, LocalDateTime startDate, String picturePath) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.startDate = startDate;
        this.picturePath = picturePath;
    }

    public Movie(String title, int duration, LocalDateTime startDate, String picturePath) {
        this.title = title;
        this.duration = duration;
        this.startDate = startDate;
        this.picturePath = picturePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", duration=" + duration + ", startDate=" + startDate + ", picturePath=" + picturePath + '}';
    }
    
    
}
