package server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.AuthenticationResult;
import common.AuthenticationValues;


public class VerificationAuthentication {
	public AuthenticationResult verifyPPAreValid(AuthenticationValues autval) {
		AuthenticationResult autRes = new AuthenticationResult();
		String query = "SELECT * FROM PJ_CLIENT WHERE PSEUDO=? AND PASSWD=?";
		try (Connection conn = ConnexionBD.getInstance()) {

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, autval.getPseudo()); // set input parameter 1
			String hashedPassword = generateHash(autval.getPasswd());
			statement.setString(2, hashedPassword); // set input parameter 2
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				autRes.setResult(true);
				autRes.setIdBase(rs.getInt("ID_CLIENT"));
				autRes.setIdGroupe(rs.getInt("ID_GROUPE"));
				return autRes;
			} else {
				autRes.setResult(false);
				return autRes;
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		autRes.setResult(false);
		return autRes;
	}

	public String generateHash(String passwordToHash) {
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(passwordToHash.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
