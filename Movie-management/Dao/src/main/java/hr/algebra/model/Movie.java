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
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    private int id;
    private String title;
    private String link;
    private String description;
    private LocalDateTime startDate;
    private String picturePath;
    
    
    public Movie(){
        
    }

    public Movie(int id, String title, String link, String description, LocalDateTime startDate, String picturePath) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.startDate = startDate;
        this.picturePath = picturePath;
    }

    public Movie(String title, String link, String description, LocalDateTime startDate, String picturePath) {
        this.title = title;
        this.link = link;
        this.description = description;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return title;
        
    }

    

 
    
}
