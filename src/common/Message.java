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
    /**
	 * 
	 */
	private static final long serialVersionUID = -3899737311956806213L;
	private Integer idClientSender;
    private Integer idClientReceiver;
    private Integer idGroupe;
    private String content;

    public Message(Integer idClientSender, Integer idClientReceiver, Integer idGroupe, String content) {
        this.idClientSender = idClientSender;
        this.idClientReceiver = idClientReceiver;
        this.idGroupe = idGroupe;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Integer getIdClientSender() {
        return idClientSender;
    }

    public Integer getIdClientReceiver() {
        return idClientReceiver;
    }

    public Integer getIdGroupe() {
        return idGroupe;
    }

	@Override
	public String toString() {
		return "Message [idClientSender=" + idClientSender + ", idClientReceiver=" + idClientReceiver + ", idGroupe="
				+ idGroupe + ", content=" + content + "]";
	}


	
    
}
