package mainGui;

import common.Authentification;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author p1920363
 */
public class AuthentificationPanel extends Parent {

    private Label titre;
    private TextField pseudo;
    private PasswordField passwd;
    private Button seconnecter;
    private Label authentificationMessage;

    public AuthentificationPanel() {
        this.titre = new Label();
        this.pseudo = new TextField();
        this.passwd = new PasswordField();
        this.seconnecter = new Button();
        this.authentificationMessage = new Label();

        titre.setLayoutX(70);
        titre.setLayoutY(35);
        titre.setPrefWidth(100);
        titre.setPrefHeight(10);
        titre.setText("Authentification");

        pseudo.setLayoutX(70);
        pseudo.setLayoutY(90);
        pseudo.setPrefWidth(160);
        pseudo.setPrefHeight(35);

        passwd.setLayoutX(70);
        passwd.setLayoutY(155);
        passwd.setPrefWidth(160);
        passwd.setPrefHeight(35);

        seconnecter.setLayoutX(70);
        seconnecter.setLayoutY(225);
        seconnecter.setPrefWidth(100);
        seconnecter.setPrefHeight(30);
        seconnecter.setText("Se connecter");
        seconnecter.setVisible(true);

        authentificationMessage.setLayoutX(70);
        authentificationMessage.setLayoutY(285);
        authentificationMessage.setPrefWidth(240);
        authentificationMessage.setPrefHeight(10);

        seconnecter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Authentification authentification = new Authentification();
                if (authentification.isValid(pseudo.getText(), passwd.getText())) {
                    Stage stage = (Stage) seconnecter.getScene().getWindow();
                    ClientPanel clientPanel = new ClientPanel();
                    Group root = new Group();
                    root.getChildren().add(clientPanel);
                    Scene scene = new Scene(root, 500, 500);
                    scene.setFill(Color.BEIGE);
                    stage.setTitle("Chat application");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    authentificationMessage.setText("Nom d'utilisateur ou mot de passe incorrect");
                    pseudo.clear();
                    passwd.clear();
                }
            }
        });

        this.getChildren().add(titre);
        this.getChildren().add(pseudo);
        this.getChildren().add(passwd);
        this.getChildren().add(seconnecter);
        this.getChildren().add(authentificationMessage);
    }
}
