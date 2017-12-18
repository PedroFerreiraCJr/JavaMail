package br.com.dotofcodex.main;

import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

public class StoreMessages {

	public static void main(String[] args) {

		final String username = "EMAIL";
		final String password = "SENHA";

		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", username, password);

			Folder[] folders = store.getDefaultFolder().list("*");

			for (Folder f : folders) {
				if (f.getName().equals("Certificados FATENE")) {
					f.open(Folder.READ_ONLY);
					for (Message m : f.getMessages()) {
						Object o = m.getContent();
						if (o instanceof Multipart) {
							Multipart mp = (Multipart) o;
							for (int j = 0; j < mp.getCount(); j++) {
								BodyPart body = mp.getBodyPart(j);

								if (body.getContentType().contains("multipart")) {
									mp = (Multipart) body.getContent();
									continue;
								}

								if (body.getContentType().contains("TEXT/HTML")) {
									System.out.println(body.getContent());
									continue;
								}

								if (body.getContentType().contains("text/plain")) {
									System.out.println(body.getContent());
									continue;
								}
							}
						}
					}
				}
			}

			System.out.println("Done");
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
