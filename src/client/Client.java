/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.AuthenticationValues;
import common.Message;
import mainGui.AuthentificationPanel;
import mainGui.ClientPanel;

/**
 *
 * @author p1920363
 */
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7072112845658492880L;
	/**
	 * 
	 */
	
	private String address;
	private int port;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private Integer idBase;
	private String pseudo;
	private Integer idGroupe;

	private ClientPanel clientPanel;
	private AuthentificationPanel authentificationPanel;

	public Client(String address, int port) {
		try {
			this.address = address;
			this.port = port;
			this.socket = new Socket(address, port);
			this.out = new ObjectOutputStream(socket.getOutputStream());
			Thread threadClientReceive = new Thread(new ClientReceive(socket, this));
			threadClientReceive.start();
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void disconnectedServer() {
		try {
			this.out.close();
			this.socket.close();
			if (this.in != null) {
				this.in.close();
			}
			System.exit(0);
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void sendMessage(String text) {
		try {
			Message mess = new Message(idBase, -1, idGroupe, text);
			this.out.writeObject(mess);
			this.out.flush();
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public ClientPanel getClientPanel() {
		return clientPanel;
	}

	public void setClientPanel(ClientPanel clientPanel) {
		this.clientPanel = clientPanel;
	}

	public AuthentificationPanel getAuthentificationPanel() {
		return authentificationPanel;
	}

	public void setAuthentificationPanel(AuthentificationPanel authentificationPanel) {
		this.authentificationPanel = authentificationPanel;
	}

	public int getIdGroupe() {
		return idGroupe;
	}

	public void demandeConnexion(AuthenticationValues authenticationValues) {
		try {
			this.out.writeObject(authenticationValues);
			this.out.flush();
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void setIdBase(Integer idBase) {
		this.idBase = idBase;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public Integer getIdBase() {
		return idBase;
	}

	public String getPseudo() {
		return pseudo;
	}

}
