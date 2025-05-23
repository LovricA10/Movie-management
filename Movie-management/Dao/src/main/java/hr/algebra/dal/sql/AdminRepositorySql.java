/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.AdminRepository;
import java.sql.CallableStatement;
import java.sql.Connection;

/**
 *
 * @author Lovric
 */
public class AdminRepositorySql implements AdminRepository {

    private static final String DELETE_ALL_DATA = "{ CALL deleteAllData }";

    @Override
    public void deleteAllData() throws Exception {
        try (Connection con = DataSourceSingleton.getInstance().getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ALL_DATA)) {
            stmt.executeUpdate();
        }
    }

}
