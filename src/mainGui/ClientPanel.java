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
	private ScrollPane scrollReceivedText;
	private TextFlow receivedText;
	private Button sendBtn;
	private Button clearBtn;
	private Client client;

	public ClientPanel() {
		this.textToSend = new TextArea();
		this.scrollReceivedText = new ScrollPane();
		this.receivedText = new TextFlow();
		this.sendBtn = new Button();
		this.clearBtn = new Button();
		client = new Client("127.0.0.1", 2001, this);

        receivedText.setLayoutX(0);
        receivedText.setLayoutY(0);
        receivedText.setVisible(true);
		
		scrollReceivedText.setLayoutX(50);
		scrollReceivedText.setLayoutY(25);
		scrollReceivedText.setPrefWidth(400);
		scrollReceivedText.setPrefHeight(350);
		scrollReceivedText.setContent(receivedText);
		scrollReceivedText.vvalueProperty().bind(receivedText.heightProperty());

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
//				Label objetLabel = new Label(textToSend.getText());
//				receivedText.getChildren().add(objetLabel);
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

		this.getChildren().add(scrollReceivedText);
		this.getChildren().add(textToSend);
		this.getChildren().add(clearBtn);
		this.getChildren().add(sendBtn);

	}
	
	public void showMessage(Message mess) {
        receivedText.getChildren().add(new Label(mess.toString()));
    }

}
