/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculationsca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lizam
 */
public class DatabaseSetup {
    final static String dbBaseURL = "jdbc:mysql://localhost";
    final static String USER = "OOC2023";
    final static String PASSWORD = "ooc2023";
    final static String dbName = "TaxCalculation";
    
    public boolean setupDB()throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    try(
         Connection conn = DriverManager.getConnection(dbBaseURL,USER,PASSWORD);
         Statement stmt = conn.createStatement();
            
            ){
            stmt.execute("CREATE DATABASE IF NOT EXISTS " + dbName+ ";");
            stmt.execute("USE " + dbName + ";");
            String sql = 
                    "CREATE TABLE IF NOT EXISTS " + dbName + " ("
                + "userName VARCHAR(255),"
                + "role VARCHAR(20),"
                + "email VARCHAR(100),"
                + "password VARCHAR(12)"
                + ");";
        stmt.execute(sql);
        return true;
      } catch (Exception e){
          e.printStackTrace();
          return false;
      }   
   }
}