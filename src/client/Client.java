/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import common.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainGui.ClientPanel;

/**
 *
 * @author p1920363
 */
public class Client {

    private String address;
    private int port;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ClientPanel clientPanel;
    private Integer idBase;
    private String pseudo;

    public Client(String address, int port,ClientPanel clientPanel, Integer idBase, String pseudo) {
        try {
            this.address = address;
            this.port = port;
            this.socket = new Socket(address, port);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.clientPanel=clientPanel;
            this.idBase= idBase;
            this.pseudo=pseudo;
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

    public void sendMessage(String text){
        try {
            Message mess= new Message("client", text);
            this.out.writeObject(mess);
            this.out.flush();
            } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ClientPanel getClientPanel() {
		return clientPanel;
	}

    
    

    
    
}
