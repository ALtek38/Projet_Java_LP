package server;

import common.AuthenticationResult;
import common.AuthenticationValues;
import common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author p1920363
 */
public class Connection implements Runnable {

	private Server server;
	private ServerSocket serverSocket;

	public Connection(Server server) {
		try {
			this.server = server;
			this.serverSocket = new ServerSocket(server.getPort());
		} catch (IOException ex) {
			Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void run() {
		boolean isActive = true;
		System.out.println("Attente de nouveaux clients");
		while (isActive) {
			try {
				Socket sockNewClient = serverSocket.accept();
				ConnectedClient newConnectedClient = new ConnectedClient(server, sockNewClient);
				Thread threadNewConnectedClient = new Thread(newConnectedClient);
				threadNewConnectedClient.start();
				
			} catch (IOException ex) {
				Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
