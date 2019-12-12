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
import javafx.scene.text.Text;
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
		Scene scene = new Scene(root, 600, 500);
		stage.setTitle("Mon application");
		stage.setScene(scene);
		stage.show();
		}
	
	/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(300);
        primaryStage.setHeight(300);
        primaryStage.setTitle("App chat");
        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BEIGE);
        Text text = new Text(10, 30, "Hello world");
        root.getChildren().add(text);
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/

}
