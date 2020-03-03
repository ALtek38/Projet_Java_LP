package mainGui;

import java.util.ArrayList;

import client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ClientPanel extends Parent {
	private TextArea textToSend;
	private TextArea receivedText;
	private TextArea connectedClients;
	private Button sendBtn;
	private Button clearBtn;
	private Button disconnectionBtn;
	private Client client;

	public ClientPanel() {
		this.textToSend = new TextArea();
		this.receivedText = new TextArea();
		this.connectedClients = new TextArea();
		this.sendBtn = new Button();
		this.clearBtn = new Button();
		this.disconnectionBtn = new Button();

		receivedText.setLayoutX(40);
		receivedText.setLayoutY(25);
		receivedText.setPrefWidth(400);
		receivedText.setPrefHeight(350);
		receivedText.setEditable(false);

		textToSend.setLayoutX(40);
		textToSend.setLayoutY(400);
		textToSend.setPrefHeight(70);
		textToSend.setPrefWidth(310);

		connectedClients.setLayoutX(445);
		connectedClients.setLayoutY(25);
		connectedClients.setPrefWidth(55);
		connectedClients.setPrefHeight(350);
		connectedClients.setEditable(false);

		sendBtn.setLayoutX(370);
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
		disconnectionBtn.setLayoutY(400);
		disconnectionBtn.setPrefHeight(30);
		disconnectionBtn.setPrefWidth(70);
		disconnectionBtn.setText("Log out");
		disconnectionBtn.setVisible(true);

		disconnectionBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				client.sendMessage("bye");
			}
		});

		this.getChildren().add(textToSend);
		this.getChildren().add(receivedText);
		this.getChildren().add(connectedClients);
		this.getChildren().add(clearBtn);
		this.getChildren().add(sendBtn);
		this.getChildren().add(disconnectionBtn);
	}

	public void updateReceivedText(String message) {
		StringBuilder fieldContent = new StringBuilder(receivedText.getText());
		fieldContent.append(message + "\n");
		receivedText.setText(fieldContent.toString());
	}

	public void updateConnectedClients(Object obj) {
		ArrayList<String> pseudosconnectedClients = (ArrayList<String>) obj;
		StringBuilder fieldContent = new StringBuilder();
		for (String pseudo : pseudosconnectedClients) {
			fieldContent.append(pseudo + "\n");
		}
		connectedClients.setText(fieldContent.toString());
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
