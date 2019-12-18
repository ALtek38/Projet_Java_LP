/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import common.Message;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author p1920363
 */
public class Server {

	int port;
	List<ConnectedClient> clients;

	public Server(int port) {
		this.port = port;
		this.clients = new ArrayList<ConnectedClient>();
		Thread threadConnection = new Thread(new Connection(this));
		threadConnection.start();
	}

	public int getPort() {
		return port;
	}

	public void broadcastMessage(Message mess, int id) {
		for (ConnectedClient client : clients) {
				client.sendMessage(mess);
		}
	}

	public void disconnectedClient(ConnectedClient connectedClient) {
		connectedClient.closeClient();
		clients.remove(connectedClient);
		for (ConnectedClient client : clients) {
			client.sendMessage(new Message("Serveur", "Le client " + connectedClient.getId() + " nous a quitté"));
		}
	}

	public void addClient(ConnectedClient newClient) {
		Message mess = new Message("Serveur", "Le client " + newClient.getId() + " vient de se connecter");
		for (ConnectedClient client : clients) {
			client.sendMessage(mess);
		}
		this.clients.add(newClient);
	}

}
