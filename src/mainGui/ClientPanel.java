package mainGui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;
import client.Client;
import common.Message;

public class ClientPanel extends Parent {
	private TextArea textToSend;
	private TextArea receivedText;
	private Button sendBtn;
	private Button clearBtn;
	private Client client;

	public ClientPanel() {
		this.textToSend = new TextArea();
		this.receivedText = new TextArea();
		this.sendBtn = new Button();
		this.clearBtn = new Button();
		client = new Client("127.0.0.1", 2001, this);

		receivedText.setLayoutX(50);
		receivedText.setLayoutY(25);
		receivedText.setPrefWidth(400);
		receivedText.setPrefHeight(350);
		receivedText.setEditable(false);

		textToSend.setLayoutX(50);
		textToSend.setLayoutY(400);
		textToSend.setPrefHeight(70);
		textToSend.setPrefWidth(300);

		sendBtn.setLayoutX(380);
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

		clearBtn.setLayoutX(380);
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

		this.getChildren().add(textToSend);
		this.getChildren().add(receivedText);
		this.getChildren().add(clearBtn);
		this.getChildren().add(sendBtn);
	}
	
	public void updateReceivedText(Message mess) {
		StringBuilder fieldContent = new StringBuilder(receivedText.getText());
		fieldContent.append(mess.toString()+"\n");
        receivedText.setText(fieldContent.toString());
    }

}
