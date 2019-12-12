/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;

/**
 *
 * @author p1920363
 */
public class Message implements Serializable{
    private String sender;
    private String content;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" + "sender=" + sender + ", content=" + content + '}';
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    
    
}
