/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author lecturer
 */
public final class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private /*Boolean*/ String role;

    
    public User(){
    }
    public User(int id, String username, String email, String password, /*Boolean*/ String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    
    public User(String username, String email, String password, /*Boolean*/ String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        
        
        
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public /*Boolean*/ String getRole() {
        return role;
    }

    public void setRole(/*Boolean*/ String role) {
        this.role = role;
    }
    
    
}
