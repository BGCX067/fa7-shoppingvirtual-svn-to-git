package br.com.anuncia.controle;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.anuncia.negocio.LoginFachada;
import br.com.anuncia.negocio.modelo.Administrador;
import br.com.anuncia.negocio.modelo.Anunciante;
import br.com.anuncia.negocio.modelo.Usuario;

public class LoginBean {
	private String login;
	private String senha;
	private String perfil;
	private String mensagemDeErro;
	private String titulo;
	private String rotuloLogin;

	/**
	 * @return Returns the rotuloLogin.
	 */
	public String getRotuloLogin() {
		return rotuloLogin;
	}

	/**
	 * @param rotuloLogin
	 *            The rotuloLogin to set.
	 */
	public void setRotuloLogin(String rotuloLogin) {
		this.rotuloLogin = rotuloLogin;
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
	}

	private void pegaParametroPerfil() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		setPerfil(resquestParams.get("perfil").toString());
	}

	public String logar() throws Exception {
		String retorno = null;
		mensagemDeErro = null;
		try {
			LoginFachada loginFachada = new LoginFachada();
			Object objeto = loginFachada.login(login, senha, perfil);
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			if (objeto != null) {
				if (perfil.equals("usuario")) {
					ManterUsuarioBean manterUsuarioBean = new ManterUsuarioBean();
					manterUsuarioBean.setUsuario((Usuario) objeto);
					session
							.setAttribute("manterUsuarioBean",
									manterUsuarioBean);
					retorno = "logar_usuario";
				} else {
					if (perfil.equals("anunciante")) {
						ManterAnuncianteBean manterAnuncianteBean = new ManterAnuncianteBean();
						manterAnuncianteBean.setAnunciante((Anunciante) objeto);
						session.setAttribute("manterAnuncianteBean",
								manterAnuncianteBean);
						retorno = "logar_anunciante";
					} else {
						if (perfil.equals("administrador")) {
							ManterAdministradorBean manterAdministradorBean = new ManterAdministradorBean();
							manterAdministradorBean
									.setAdministrador((Administrador) objeto);
							session.setAttribute("manterAdministradorBean",
									manterAdministradorBean);
							retorno = "logar_administrador";
						}
					}
				}
			}
		} catch (Exception e) {
			mensagemDeErro = e.getMessage();
		}
		return retorno;
	}

	public String iniciar() {
		limpar_login();
		pegaParametroPerfil();

		rotuloLogin = new String();

		if (perfil.equals("usuario")) {
			titulo = "Logar como Usuario";
			rotuloLogin = "Email";
		} else {
			if (perfil.equals("anunciante")) {
				titulo = "Logar como Anunciante";
				rotuloLogin = "Email";
			} else {
				if (perfil.equals("administrador")) {
					titulo = "Logar como Administrador";
					rotuloLogin = "Login";
				}
			}
		}

		return "login";
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

	public String sair() throws Exception {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.removeAttribute("manterUsuarioBean");
		session.removeAttribute("manterAnuncianteBean");
		session.removeAttribute("manterAdministradorBean");

		return "logoff";
	}

}
