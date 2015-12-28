package br.com.seteshop.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AuthenticatorFA7 extends Authenticator{
	private String from;
	private String password;
	public AuthenticatorFA7(String from,String password){
		this.from = from;
		this.password = password;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(from,password);
	}

	public final String getFrom() {
		return from;
	}

	public final void setFrom(String from) {
		this.from = from;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}
}
