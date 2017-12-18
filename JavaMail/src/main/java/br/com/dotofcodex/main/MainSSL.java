package br.com.dotofcodex.main;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MainSSL {

	public static void main(String[] args) {

		final String username = "EMAIL";
		final String password = "SENHA";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			// Configura a mensagem a ser enviada
			Message message = new MimeMessage(session);
			// Configura o remetente da mensagem
			message.setFrom(new InternetAddress("pedroferreiracjr@gmail.com"));
			// Configura o destinatário da mensagem
			// message.setRecipients(Message.RecipientType.TO, new Address[] {
			// new InternetAddress("pedroferreiracjr@gmail.com") });
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("pedroferreiracjr@gmail.com"));
			// Titulo da mensagem
			message.setSubject("Nova Mensagem");
			// Ou setContent configura o conteudo da mensagem
			message.setText("Pedro Ferreira,\n\n Teste de envio de mensagem sem anexo!");
			// Configura a data de envio
			message.setSentDate(new Date());

			// Adiciona o texto da mensagem
			BodyPart bp1 = new MimeBodyPart();
			// bp1.setContent("Enviando arquivo anexado por email",
			// "text/plain");
			bp1.setText("Envio de mensagem simples");

			Multipart mm = new MimeMultipart();
			mm.addBodyPart(bp1);

			message.setContent(mm);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
