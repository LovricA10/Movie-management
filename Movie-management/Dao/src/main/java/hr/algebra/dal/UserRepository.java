/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dnlbe
 */
public interface UserRepository {
    
    int createUser(User user) throws Exception;
    void createUsers(List<User> users) throws Exception;
    void updateUser(int id, User data) throws Exception;
    void deleteUser(int id) throws Exception;
    Optional<User> selectUser(int id) throws Exception;
    List<User> selectUsers() throws Exception;
}
