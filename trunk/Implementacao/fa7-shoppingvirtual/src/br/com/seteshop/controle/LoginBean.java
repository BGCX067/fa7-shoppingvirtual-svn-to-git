package br.com.seteshop.controle;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
 
import br.com.seteshop.negocio.ClienteFachada;
import br.com.seteshop.negocio.LojistaFachada;
import br.com.seteshop.negocio.modelo.Cliente;
import br.com.seteshop.negocio.modelo.Lojista;
import br.com.seteshop.negocio.modelo.Usuario;

public class LoginBean {
	private String login;
	private String senha;
	private String perfil;
	private String mensagemDeErro;
	private String titulo;
	private Cliente cliente;
	private Lojista lojista;
	private String admin;
	
	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Lojista getLojista() {
		return lojista;
	}

	public void setLojista(Lojista lojista) {
		this.lojista = lojista;
	}

	/**
	 * @return Returns the titulo.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            The titulo to set.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	private void limpar_login() {
		mensagemDeErro = null;
		login = null;
		senha = null;

		cliente = null;
		lojista = null;
		admin = null;
	}

	public LoginBean() {
	}

	public String logarComoCliente() throws Exception {
		try {

			ClienteFachada clientefachada = new ClienteFachada();

			cliente = null;

			cliente = clientefachada.recuperarClientePorEmailESenha(login,
					senha);

			Cliente cliente_temp = cliente;

			limpar_login();

			cliente = cliente_temp;

			if (cliente != null) {
				return "sucesso_cliente";
			} else {
				return "error";
			}
		} catch (Exception e) {

		}
		return "";
	}

	public String logarComoLojista() throws Exception {
		try {

			LojistaFachada lojistafachada = new LojistaFachada();

			lojista = null;

			lojista = lojistafachada.recuperarLojistaPorEmailESenha(login,
					senha);

			Lojista lojista_temp = lojista;

			limpar_login();

			lojista = lojista_temp;

			if (lojista != null) {
				return null;
			} else {
				return "error";
			}
		} catch (Exception e) {

		}
		return "";
	}
	
	public String logarComoAdmin() throws Exception {

		admin = null;
		if ((login.equals("zzz")) && (senha.equals("zzz"))) {
			admin = login;
		}
		return "";
	}

	/**
	 * @return Returns the mensagemDeErro.
	 */
	public String getMensagemDeErro() {
		return mensagemDeErro;
	}

	/**
	 * @param mensagemDeErro
	 *            The mensagemDeErro to set.
	 */
	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}

	public String sair() {

		limpar_login();

		return "logoff";
	}

	public String sairLojista() {

		limpar_login();

		return "logoff_lojista";
	}

	public String sairAdmin() {

		limpar_login();

		return "logoff_admin";
	}

}
