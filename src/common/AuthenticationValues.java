package common;

import java.io.Serializable;

public class AuthenticationValues implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7264676270852068752L;
	private String pseudo;
    private String passwd;

    public AuthenticationValues(String pseudo, String passwd) {
        this.pseudo = pseudo;
        this.passwd = passwd;
    }

	public String getPseudo() {
		return pseudo;
	}

	public String getPasswd() {
		return passwd;
	}
    
    
}