package common;

import java.io.Serializable;

public class AuthenticationResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6876207216434651095L;
	private Boolean Result;// = true : successful authentication 
	private Integer idBase;
	private Integer idGroupe;
	public void setResult(Boolean result) {
		Result = result;
	}
	public void setIdBase(Integer idBase) {
		this.idBase = idBase;
	}
	
	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}
	public Boolean getResult() {
		return Result;
	}
	public Integer getIdBase() {
		return idBase;
	}
	public Integer getIdGroupe() {
		return idGroupe;
	}
	
	
}
