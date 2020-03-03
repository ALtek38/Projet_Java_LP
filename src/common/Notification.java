package common;

import java.io.Serializable;

public class Notification implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9008277071258646128L;
	private String content;

	public Notification(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
