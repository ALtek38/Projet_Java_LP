package server;

import java.io.IOException;
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
        System.out.println("Attente de nouveaux clients");
        while (true) {
            try {
                Socket sockNewClient = serverSocket.accept();
                ConnectedClient newClient = new ConnectedClient(server, sockNewClient);
                server.addClient(newClient);
                Thread threadNewClient = new Thread(newClient);
                threadNewClient.start();
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
