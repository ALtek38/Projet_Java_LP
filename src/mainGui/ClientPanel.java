package mainGui;

import java.util.ArrayList;

import client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ClientPanel extends Parent {
	private TextArea textToSend;
	private TextArea receivedText;
	private TextArea connectedUsers;
	private Button sendBtn;
	private Button clearBtn;
	private Button disconnectionBtn;
	private Client client;
        private Button emoteBtn;
        private Popup emotePP;
        private Label emoteLabel;
        private Button emote1;
        private Button emote2;
        private Button emote3;
        private Button emote4;
        private Button emote5;
        private final Stage stage;
        

	public ClientPanel(Stage stage) {
            this.stage = stage;
		this.textToSend = new TextArea();
		this.receivedText = new TextArea();
		this.connectedUsers = new TextArea();
		this.sendBtn = new Button();
		this.clearBtn = new Button();
		this.disconnectionBtn = new Button();
                this.emoteBtn = new Button();
                this.emotePP = new Popup();
                this.emoteLabel = new Label("");
                this.emote1 = new Button("ಠ▃ಠ");
                this.emote2 = new Button("（ꉺᗜꉺ）");
                this.emote3 = new Button("۹(ÒہÓ)۶");
                this.emote4 = new Button("(ಥ﹏ಥ)");
                this.emote5 = new Button("(∩｀-´)⊃━☆ﾟ.*･｡ﾟ");
		client = new Client("127.0.0.1", 2001, this);

		receivedText.setLayoutX(40);
		receivedText.setLayoutY(25);
		receivedText.setPrefWidth(400);
		receivedText.setPrefHeight(350);
		receivedText.setEditable(false);

		textToSend.setLayoutX(40);
		textToSend.setLayoutY(400);
		textToSend.setPrefHeight(70);
		textToSend.setPrefWidth(310);

		connectedUsers.setLayoutX(445);
		connectedUsers.setLayoutY(25);
		connectedUsers.setPrefWidth(3);
		connectedUsers.setPrefHeight(55);
		connectedUsers.setEditable(false);

		sendBtn.setLayoutX(390);
		sendBtn.setLayoutY(400);
		sendBtn.setPrefHeight(30);
		sendBtn.setPrefWidth(70);
		sendBtn.setText("Send");
		sendBtn.setVisible(true);

		sendBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				client.sendMessage(textToSend.getText());
				textToSend.clear();
			}
		});

		clearBtn.setLayoutX(370);
		clearBtn.setLayoutY(437);
		clearBtn.setPrefHeight(30);
		clearBtn.setPrefWidth(70);
		clearBtn.setText("Clear");
		clearBtn.setVisible(true);

		clearBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				textToSend.clear();
			}
		});

		disconnectionBtn.setLayoutX(445);
		disconnectionBtn.setLayoutY(90);
		disconnectionBtn.setPrefHeight(5);
		disconnectionBtn.setPrefWidth(25);
		disconnectionBtn.setText("D");
		disconnectionBtn.setVisible(true);
		
		disconnectionBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				client.sendMessage("bye");
			}
		});
                
                emoteLabel.setMinWidth(110); 
                emoteLabel.setMinHeight(100); 
                emoteLabel.setStyle(" -fx-background-color: white;"); 

                emote1.setLayoutX(5);
                emote1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    textToSend.setText(textToSend.getText()+" ಠ▃ಠ");
                }});
                emote1.setLayoutY(5);
                
                emote2.setLayoutX(50);
                emote2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    textToSend.setText(textToSend.getText()+" （ꉺᗜꉺ）");
                }});
                emote2.setLayoutY(5);
                
                emote3.setLayoutX(5);
                emote3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    textToSend.setText(textToSend.getText()+" ۹(ÒہÓ)۶");
                }});
                emote3.setLayoutY(30);
                
                emote4.setLayoutX(50);
                emote4.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    textToSend.setText(textToSend.getText()+" (ಥ﹏ಥ)");
                }});
                emote4.setLayoutY(30);
                
                emote5.setLayoutX(5);
                emote5.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    textToSend.setText(textToSend.getText()+" (∩｀-´)⊃━☆ﾟ.*･｡ﾟ");
                }});
                emote5.setLayoutY(55);
                
                emotePP.hide();
                emotePP.getContent().add(emoteLabel);
                emotePP.getContent().add(emote1);
                emotePP.getContent().add(emote2);
                emotePP.getContent().add(emote3);
                emotePP.getContent().add(emote4);
                emotePP.getContent().add(emote5);
                
                emoteBtn.setLayoutX(350);
                emoteBtn.setLayoutY(400);
                emoteBtn.setText(":-)");
                emoteBtn.setVisible(true);
                
                emoteBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                        if (!emotePP.isShowing()) {
                            emotePP.setAnchorX(t.getScreenX());
                            emotePP.setAnchorY(t.getScreenY());
                            emotePP.show(stage); 
                        }
                        else
                            emotePP.hide(); 
                        }
                  });
                
		this.getChildren().add(textToSend);
		this.getChildren().add(receivedText);
		this.getChildren().add(connectedUsers);
		this.getChildren().add(clearBtn);
		this.getChildren().add(sendBtn);
		this.getChildren().add(disconnectionBtn);
                this.getChildren().add(emoteBtn);
	}


	public void updateReceivedText(String message) {
		StringBuilder fieldContent = new StringBuilder(receivedText.getText());
		fieldContent.append(message + "\n");
		receivedText.setText(fieldContent.toString());
	}

	public void updateConnectedUsers(Object obj) {
		ArrayList<Integer> listConnectedUsers = (ArrayList<Integer>) obj;
		StringBuilder fieldContent = new StringBuilder();
		for (Integer i : listConnectedUsers) {
			fieldContent.append(i + "\n");
		}
		connectedUsers.setText(fieldContent.toString());
	}

}
