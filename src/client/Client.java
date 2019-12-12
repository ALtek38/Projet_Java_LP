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

//    public Client(String address, int port) {
//        try {
//            this.address = address;
//            this.port = port;
//            this.socket = new Socket(address, port);
//            this.out = new ObjectOutputStream(socket.getOutputStream());
//            Thread threadClientSend = new Thread(new ClientSend(socket, out));
//            threadClientSend.start();
//            Thread threadClientReceive = new Thread(new ClientReceive(socket, this));
//            threadClientReceive.start();
//        } catch (IOException ex) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public Client(String address, int port,ClientPanel clientPanel) {
        try {
            this.address = address;
            this.port = port;
            this.socket = new Socket(address, port);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.clientPanel=clientPanel;
            Thread threadClientSend = new Thread(new ClientSend(socket, out));
            threadClientSend.start();
            Thread threadClientReceive = new Thread(new ClientReceive(socket, this));
            threadClientReceive.start();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnectedServer() {
        try {
            this.in.close();
            this.out.close();
            if (this.socket != null) {
                this.socket.close();
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
            this.clientPanel.showMessage(mess);
            } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void messageReceived(Message mess) {
        System.out.println(mess);
    }
}
