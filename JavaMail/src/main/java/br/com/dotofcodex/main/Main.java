package br.com.dotofcodex.main;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
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

public class Main {

	public static void main(String[] args) {

		sendSimpleMessage();
	}

	public static void sendSimpleMessage() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("EMAIL", "SENHA");
			}
		});

		try {
			// Configura a mensagem a ser enviada
			Message message = new MimeMessage(session);
			// Configura o remetente da mensagem
			message.setFrom(new InternetAddress("FROM"));
			// Configura o destinatário da mensagem
			// message.setRecipients(Message.RecipientType.TO, new Address[] {
			// new InternetAddress("pedroferreiracjr@gmail.com") });
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("TO"));
			// Titulo da mensagem
			message.setSubject("New Message");
			// Ou setContent configura o conteudo da mensagem
			message.setText("Pedro Ferreira,\n\n Teste de envio de mensagem com anexo!");
			// Configura a data de envio
			message.setSentDate(new Date());

			// Adiciona o texto da mensagem
			BodyPart bp1 = new MimeBodyPart();
			// bp1.setContent("Enviando arquivo anexado por email",
			// "text/plain");
			bp1.setText("Enviando arquivo anexado por email");

			BodyPart bp2 = new MimeBodyPart();
			FileDataSource fileDataSource = new FileDataSource("CAMINHO DO ARQUIVO");
			bp2.setDataHandler(new DataHandler(fileDataSource));
			bp2.setFileName(fileDataSource.getName());

			Multipart mm = new MimeMultipart();
			mm.addBodyPart(bp1);
			mm.addBodyPart(bp2);

			message.setContent(mm);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
