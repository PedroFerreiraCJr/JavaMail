package br.com.dotofcodex.main;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.swing.JOptionPane;

public class TestConnection {

	public static void main(String[] args) {
	
		if (connect()) {
			JOptionPane.showMessageDialog(null, "Conexão estabelecida");
		}
		else {
			JOptionPane.showMessageDialog(null, "Erro na conexão");
		}
		
	}

	private static boolean connect() {
		
		boolean canConnect = false;
		final String username = "pedroferreiracjr@gmail.com";
		final String password = "p3dr0123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {
			Transport transport = session.getTransport("smtp");
			transport.connect();
			transport.close();

			canConnect = true;
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return canConnect;
	}
	
}
