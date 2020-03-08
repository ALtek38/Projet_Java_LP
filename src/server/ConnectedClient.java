/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import common.AuthenticationResult;
import common.AuthenticationValues;
import common.Message;
import common.Notification;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

/**
 *
 * @author p1920363
 */
public class ConnectedClient implements Runnable {

	private Integer idBase;
	private String pseudo;
	private Integer idGroupe;
	private Server server;
	private Socket socketClient;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public ConnectedClient(Server server, Socket socketClient) {
		try {
			this.server = server;
			this.socketClient = socketClient;
			out = new ObjectOutputStream(socketClient.getOutputStream());

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
				Object object = in.readObject();
				
				if (object != null && (object instanceof AuthenticationValues)) {
					AuthenticationValues autva = (AuthenticationValues) object;
					VerificationAuthentication verificationAuthentication = new VerificationAuthentication();
					AuthenticationResult autRes = verificationAuthentication.verifyPPAreValid(autva);
					sendAuthenticationResult(autRes);
					if (autRes.getResult()) {
						System.out.println("Successful authentication of the client = " + socketClient);
						this.idBase=autRes.getIdBase();
						this.pseudo=autva.getPseudo();
						this.server.addClient(this);
					} else
						System.out.println("Failure authentication of the client = " + socketClient);
				}
				if (object != null && (object instanceof Message)) {
					Message mess = (Message) object;
					if (mess.getContent().equals("bye")) {
						server.disconnectedClient(this);
						isActive = false;
					} else {
						saveMessageDB(mess);
						mess.setContent(this.pseudo + "> " + mess.getContent());
						server.broadcastMessage(mess);
					}
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

	public void sendMessage(Message mess) {
		try {
			this.out.writeObject(mess);
			this.out.flush();
		} catch (IOException ex) {
			Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void sendNotification(Notification notification) {
		try {
			this.out.writeObject(notification);
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

	public void sendListConnectedClients(ArrayList<String> pseudosconnectedClients) {
		try {
			this.out.writeObject(pseudosconnectedClients);
			this.out.flush();
		} catch (IOException ex) {
			Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void saveMessageDB(Message mess) {
		String query = "insert into PJ_MESSAGE values (?, ?, ?, ?, ?)";
		try (Connection conn = ConnexionBD.getInstance()) {
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, mess.getIdClientSender());
			statement.setInt(2, mess.getIdClientReceiver());
			statement.setInt(3, mess.getIdGroupe());
			statement.setString(4, mess.getContent());
			java.util.Date myDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			statement.setDate(5, sqlDate);
			statement.execute();
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}
	}

	private void sendAuthenticationResult(AuthenticationResult autRes) {
		try {
			this.out.writeObject(autRes);
			this.out.flush();
		} catch (IOException ex) {
			Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void setIdBase(Integer idBase) {
		this.idBase = idBase;
	}

	public Integer getIdBase() {
		return idBase;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

}
