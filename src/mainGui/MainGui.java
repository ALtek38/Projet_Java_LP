/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainGui;

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
		ClientPanel clientPanel = new ClientPanel();
		Group root = new Group();
		root.getChildren().add(clientPanel);
		Scene scene = new Scene(root, 500, 500);
		scene.setFill(Color.BEIGE);
		stage.setTitle("Chat application");
		stage.setScene(scene);
		stage.show();
		}
	
}
