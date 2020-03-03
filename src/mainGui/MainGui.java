/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainGui;

import client.Client;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author p1920363
 */
public class MainGui extends Application {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Application.launch(MainGui.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Client client = new Client("127.0.0.1", 2001);
        AuthentificationPanel authentificationPanel = new AuthentificationPanel(client);
        client.setAuthentificationPanel(authentificationPanel);
        Group root = new Group();
        root.getChildren().add(authentificationPanel);
        Scene scene = new Scene(root, 346, 349);
        scene.setFill(Color.BEIGE);
        stage.setTitle("Chat application");
        stage.setScene(scene);
        stage.show();
    }
}
