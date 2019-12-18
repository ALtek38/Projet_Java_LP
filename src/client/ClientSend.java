/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import common.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1920363
 */
public class ClientSend implements Runnable {

    private Socket socket;
    private ObjectOutputStream out;

    public ClientSend(Socket socket, ObjectOutputStream out) {
        this.socket = socket;
        this.out = out;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Votre message >> ");
                String m = sc.nextLine();
                Message mess = new Message("client", m);
                out.writeObject(mess);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
