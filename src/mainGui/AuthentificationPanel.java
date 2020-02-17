/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainGui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author p1920363
 */
public class AuthentificationPanel extends Parent {

    private Label titre;
    private TextField pseudo;
    private PasswordField passwd;
    private Button seconnecter;

    public AuthentificationPanel() {
        this.titre = new Label();
        this.pseudo = new TextField();
        this.passwd = new PasswordField();
        this.seconnecter = new Button();

        titre.setLayoutX(86);
        titre.setLayoutY(87);
        titre.setPrefWidth(100);
        titre.setPrefHeight(100);
        titre.setText("Authentification");
        
        pseudo.setLayoutX(86);
        pseudo.setLayoutY(126);
        pseudo.setPrefWidth(100);
        pseudo.setPrefHeight(100);
        
        passwd.setLayoutX(86);
        passwd.setLayoutY(87);
        passwd.setPrefWidth(100);
        passwd.setPrefHeight(100);
        
        seconnecter.setLayoutX(86);
        seconnecter.setLayoutY(232);
        seconnecter.setPrefWidth(100);
        seconnecter.setPrefHeight(100);
        seconnecter.setText("Se connecter");
        seconnecter.setVisible(true);
        
        this.getChildren().add(titre);
		this.getChildren().add(pseudo);
		this.getChildren().add(passwd);
		this.getChildren().add(seconnecter);
		

    }
}
