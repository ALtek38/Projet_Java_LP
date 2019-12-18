/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import common.Message;
import javafx.application.Platform;
import server.ConnectedClient;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1920363
 */
public class ClientReceive implements Runnable {

	private Socket socket;
	private Client client;
	private ObjectInputStream in;
	private Message mess;

	public ClientReceive(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;
	}

	@Override
	public void run() {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			boolean isActive = true;
			while (isActive) {
				mess = (Message) in.readObject();
				if (mess != null) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							client.getClientPanel().updateReceivedText(mess);
						}
					});
				} else {
					isActive = false;
				}
			}
			client.disconnectedServer();
		} catch (EOFException ex) {
			Logger.getLogger(ClientReceive.class.getName()).log(Level.INFO, "Client deconnected");
		} catch (IOException ex) {
			Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
