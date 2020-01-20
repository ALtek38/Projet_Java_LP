/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainGui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.application.Application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author p1920363
 */
public class Authentification {

    public static void main(String[] args) {

//        String passwordToHash = "pass1";
//        String hashedPassword = generateHash(passwordToHash);
//        System.out.println(hashedPassword);
        String sql = "SELECT * FROM PJ_CLIENT WHERE PSEUDO=? AND PASSWD=?";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@iutdoua-oracle.univ-lyon1.fr:1521:orcl", "p1618672", "281922")) {

            
            PreparedStatement statement;
            statement = conn.prepareStatement(sql);
            statement.setString(1, "login1"); // set input parameter 1
            String passwordToHash = "pass1";
            String hashedPassword = generateHash(passwordToHash);
            statement.setString(2, hashedPassword); // set input parameter 2
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("user login successfull.");
            } else {
                System.out.println("user login failed.");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String generateHash(String passwordToHash) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
