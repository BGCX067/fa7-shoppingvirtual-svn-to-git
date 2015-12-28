package br.com.seteshop.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Security;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.Authenticator;
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

public class SendMailUsingGmail {

	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String USER_NAME = "anuncia7@gmail.com";
	private static final String PASSWORD = "anunciasete";

	private static final String SMTP_PORT = "465";
	private static final String emailMsgTxt = "O MESTRE";

	private static final String emailSubjectTxt = "testando Javamail e GMail"
			+ Math.random();

	private static final String emailFromAddress = "xx@xx.com";

	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";


	private static final String filename = "C:\\Documents and Settings\\vandewilly\\Desktop\\img\\lll.zip";

	static final int TAMANHO_BUFFER = 2048; // 2kb  
	public static void main(String args[]) throws Exception {
		//COMPACTANDO ARQUIVOS
		int  cont;   
		byte[] dados = new byte[TAMANHO_BUFFER];   
		BufferedInputStream origem = null;   
		FileInputStream streamDeEntrada = null;   
		FileOutputStream destino = null;   
		ZipOutputStream saida = null;   
		ZipEntry entry = null;  
		File f = new File("C:\\Documents and Settings\\vandewilly\\Desktop\\img");
		String[] arquivos = f.list();
		String arqSaida = "C:\\Documents and Settings\\vandewilly\\Desktop\\img\\lll.zip";
		destino = new FileOutputStream(arqSaida);   
		saida = new ZipOutputStream(new BufferedOutputStream(destino));  
		String extensao;
		for (int i = 0; i < arquivos.length; i++) { 
			extensao = arquivos[i].substring(arquivos[i].lastIndexOf(".")+1);
			if(extensao.equalsIgnoreCase("jpg")){
				
			File arquivo = new File("C:\\Documents and Settings\\vandewilly\\Desktop\\img\\"+arquivos[i]);
			if (arquivo.isFile()&& !(arquivo.getName()).equals(arqSaida)) {
			streamDeEntrada = new FileInputStream(arquivo);   
			origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);   
			entry = new ZipEntry(arquivos[i]);   
			saida.putNextEntry(entry);  
			
			while((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {   
				saida.write(dados, 0, cont);   
				}   
				
				origem.close(); 
			}
			}
		}
		
		saida.close();  
		
		//////////////////////////////////////
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());


		System.out.println("Email enviado com sucesso para os destinatarios!");
	}

	public static void sendSSLMessage(String recipients[], String subject,
			String message) throws MessagingException, IOException {

		// com essa flag aqui ele imprimira todos os
		// dados da conex�o e do envio,
		// setar isso para false se quiser que rode
		// no silent mode.
		boolean debug = true;

		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		AuthenticatorFA7 authLG = new AuthenticatorFA7(USER_NAME,PASSWORD);
		Session session = Session.getDefaultInstance(props,authLG);

		session.setDebug(debug);

		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(USER_NAME);
		msg.setFrom(addressFrom);

		//TODO TESTE DE ENVIO DE EMAIL - RETIRAR DEPOIS
		
		InternetAddress[] addressTo = new InternetAddress[recipients.length];

		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}

		msg.setRecipients(Message.RecipientType.TO, addressTo);

		// Setting the Subject and Content Type
		msg.setSubject(subject);
		// msg.setContent(message, "text/plain");

		// create and fill the first message part
		MimeBodyPart mbp1 = new MimeBodyPart();
		mbp1.setText(message);


		/*
		 * Use the following approach instead of the above line if you want to
		 * control the MIME type of the attached file. Normally you should never
		 * need to do this.
		 * 
		 * FileDataSource fds = new FileDataSource(filename) { public String
		 * getContentType() { return "application/octet-stream"; } };
		 * mbp2.setDataHandler(new DataHandler(fds));
		 * mbp2.setFileName(fds.getName());
		 */

		// create the Multipart and add its parts to it
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbp1);

		// add the Multipart to the message
		msg.setContent(mp);

		Transport.send(msg);
	}
}