/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import common.Message;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1920363
 */
public class ConnectedClient implements Runnable {

	private static int idCounter;
	private int id;
	private Server server;
	private Socket socketClient;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public ConnectedClient(Server server, Socket socketClient) {
		try {
			this.server = server;
			this.socketClient = socketClient;
			this.id = idCounter++;
			out = new ObjectOutputStream(socketClient.getOutputStream());
			System.out.println("Nouvelle connexion, id = " + id);
		} catch (IOException ex) {
			Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void run() {
		try {
			in = new ObjectInputStream(socketClient.getInputStream());
			boolean isActive = true;
			while (isActive) {
				Message mess = (Message) in.readObject();
				if (mess != null) {
					if (mess.getContent().equals("bye")) {
						server.disconnectedClient(this);
						isActive = false;
					} else {
						mess.setSender(mess.getSender() + " " + String.valueOf(id));
						server.broadcastMessage(mess);
					}
				} else {
					server.disconnectedClient(this);
					isActive = false;
				}
			}
		} catch (EOFException ex) {
			Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void sendMessage(Message mess) {
		try {
			this.out.writeObject(mess);
			this.out.flush();
		} catch (IOException ex) {
			Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void closeClient() {
		try {
			this.in.close();
			this.out.close();
			this.socketClient.close();
		} catch (IOException ex) {
			Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void sendListConnectedClients(ArrayList<Integer> idsconnectedClients) {
		try {
			this.out.writeObject(idsconnectedClients);
			this.out.flush();
		} catch (IOException ex) {
			Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
}
