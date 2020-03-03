/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import common.Message;
import common.Notification;

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
			client.sendNotification(new Notification("Le client " + connectedClient.getPseudo() + " nous a quitté"));
			client.sendListConnectedClients(getPseudosConnectedClients());
		}
		connectedClient = null;
	}

	public void addClient(ConnectedClient newClient) {
		this.clients.add(newClient);
		for (ConnectedClient client : clients) {
			if (client.getIdBase() != newClient.getIdBase())
				client.sendNotification(new Notification("Le client "+ newClient.getPseudo() +  " vient de se connecter"));
			client.sendListConnectedClients(getPseudosConnectedClients());
		}
	}

	private ArrayList<String> getPseudosConnectedClients() {
		ArrayList<String> pseudosconnectedClients = new ArrayList<String>();
		for (ConnectedClient client : clients) {
			pseudosconnectedClients.add(client.getPseudo());
		}
		return pseudosconnectedClients;
	}
}
