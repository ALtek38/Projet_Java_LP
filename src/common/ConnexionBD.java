/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author p1920363
 */
public final class ConnexionBD {

    private static ConnexionBD uniqueInstance = null;

    //URL de connexion
    private String url = "jdbc:oracle:thin:@iutdoua-oracle.univ-lyon1.fr:1521:orcl";
    //Nom du user
    private String user = "p1618672";
    //Mot de passe de l'utilisateur
    private String passwd = "281922";
    //Objet Connection
    private static Connection connect;
    

    private ConnexionBD() {
        try {
            connect = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void releaseInstance() {
        uniqueInstance = null; // libère l'instance
    }

    //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
    public static synchronized Connection getInstance() {
        if (connect == null) {
            new ConnexionBD();
        }
        return connect;
    }
}
