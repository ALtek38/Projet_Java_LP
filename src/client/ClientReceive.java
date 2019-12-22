/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.Message;
import javafx.application.Platform;

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
				Object object = in.readObject();
				if (object != null) {
					if (object instanceof Message) {
						mess = (Message) object;
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								client.getClientPanel().updateReceivedText(mess.toString());
							}
						});
					} else if (object instanceof ArrayList) {

						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								client.getClientPanel().updateConnectedUsers(object);
							}
						});
					}
				} else {
					isActive = false;
				}
			}
			client.disconnectedServer();
		} catch (EOFException ex) {
			client.disconnectedServer();
			Logger.getLogger(ClientReceive.class.getName()).log(Level.INFO, "Client deconnected");
		} catch (IOException ex) {
			Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
