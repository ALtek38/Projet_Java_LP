<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainGui;
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.control.Label; 
import javafx.stage.Stage; 
import javafx.stage.Popup; 

/**
 *
 * @author P1618672
 */   
public class EmotePopup extends Application { 
   
    // launch the application 
    public void start(Stage stage) 
    { 
   
        // set title for the stage 
        stage.setTitle("Creating popup"); 
   
        // create a button 
        Button button = new Button("button"); 
   
        // create a tile pane 
        TilePane tilepane = new TilePane(); 
   
        // create a label 
        Label label = new Label("This is a Popup"); 
   
        // create a popup 
        Popup popup = new Popup(); 
   
        // set background 
        label.setStyle(" -fx-background-color: white;"); 
   
        // add the label 
        popup.getContent().add(label); 
   
        // set size of label 
        label.setMinWidth(80); 
        label.setMinHeight(50); 
   
        // action event 
        EventHandler<ActionEvent> event =  
        new EventHandler<ActionEvent>() { 
   
            public void handle(ActionEvent e) 
            { 
                if (!popup.isShowing()) 
                    popup.show(stage); 
                else
                    popup.hide(); 
            } 
        }; 
   
        // when button is pressed 
        button.setOnAction(event); 
   
        // add button 
        tilepane.getChildren().add(button); 
   
        // create a scene 
        Scene scene = new Scene(tilepane, 200, 200); 
   
        // set the scene 
        stage.setScene(scene); 
   
        stage.show(); 
    } 
   
    // Main Method 
    public static void main(String args[]) 
    { 
   
        // launch the application 
        launch(args); 
    } 
} 
    
=======
>>>>>>> 548b3b5aa0bcb5ebc6b2cf8938678395cc1f6589
