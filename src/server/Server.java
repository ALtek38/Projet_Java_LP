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

	private int port;
	private List<ConnectedClient> clients;

	public Server(int port) {
		this.port = port;
		this.clients = new ArrayList<ConnectedClient>();
		Thread threadConnection = new Thread(new Connection(this));
		threadConnection.start();
	}

	public int getPort() {
		return port;
	}

	public void broadcastMessage(Message mess) {
		for (ConnectedClient client : clients) {
			client.sendMessage(mess);
		}
	}

	public void disconnectedClient(ConnectedClient connectedClient) {
		connectedClient.closeClient();
		clients.remove(connectedClient);
		for (ConnectedClient client : clients) {
			client.sendMessage(new Message("Serveur", "Le client " + connectedClient.getId() + " nous a quitté"));
			client.sendListConnectedClients(getIdsConnectedClients());
		}
		connectedClient = null;
	}

	public void addClient(ConnectedClient newClient) {
		this.clients.add(newClient);
		Message mess = new Message("Serveur", "Le client " + newClient.getId() + " vient de se connecter");
		for (ConnectedClient client : clients) {
			if (client.getId() != newClient.getId())
				client.sendMessage(mess);
			client.sendListConnectedClients(getIdsConnectedClients());
		}
	}

	private ArrayList<Integer> getIdsConnectedClients() {
		ArrayList<Integer> idsconnectedClients = new ArrayList<Integer>();
		for (ConnectedClient client : clients) {
			idsconnectedClients.add(client.getId());
		}
		return idsconnectedClients;
	}
}
