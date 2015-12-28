package br.com.anuncia.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String SMTP_PORT = "465";
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private static final String USER_NAME = "anuncia7@gmail.com";
	private static final String PASSWORD = "anunciasete";

	public void SendMessage(String emailSubjectTxt, String emailMsgTxt,
			String... sendTo) {
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.quitwait", "false");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(USER_NAME, PASSWORD);
					}
				});

		session.setDebug(true);
		Message msg = new MimeMessage(session);

		try {
			msg.setFrom(new InternetAddress(USER_NAME));

			InternetAddress[] addressTo = new InternetAddress[sendTo.length];
			for (int i = 0; i < sendTo.length; i++) {
				addressTo[i] = new InternetAddress(sendTo[i]);
			}

			msg.setRecipients(Message.RecipientType.TO, addressTo);

			msg.setSubject(emailSubjectTxt);
			msg.setContent(emailMsgTxt, "text/plain");
			Transport.send(msg);

		} catch (MessagingException e) {
			System.out.println("ERRO =" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		SendMail sd = new SendMail();
		sd.SendMessage("Teste","Teste Mensagem","alyssonbandeira@gmail.com");
		sd.SendMessage("Teste3333333","Teste Mensagem","alyssonbandeira@gmail.com");
	}
}
