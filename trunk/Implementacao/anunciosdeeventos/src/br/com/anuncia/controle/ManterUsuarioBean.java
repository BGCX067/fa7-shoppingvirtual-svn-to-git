/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.controle;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.anuncia.negocio.UsuarioFachada;
import br.com.anuncia.negocio.modelo.Usuario;


public class ManterUsuarioBean {

	// Objetos utilizados pela página
	private Long id;
	private Usuario usuario;
	private String confirmarSenha;
	private String mensagemDeErro;
	
	// Objetos para acesso ao negócio
	private UsuarioFachada usuarioFachada;
	
	public ManterUsuarioBean() {
		// TODO Auto-generated constructor stub
		usuarioFachada = new UsuarioFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (usuario == null) {
			usuario = new Usuario();
		}
		
		if (confirmarSenha == null) {
			confirmarSenha = new String();
		}
		
		if (usuarioFachada == null) {
			usuarioFachada = new UsuarioFachada();
		}
	}
	
	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Returns the usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario The usuario to set.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	private void limpar_usuario() {
		usuario = new Usuario();
	}
	
	public String incluir() {
		setMensagemDeErro("");
		limpar_usuario();
		return "incluir_usuario";
	}
	
	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}

	public String salvar_novo() {
		setMensagemDeErro("");
		
		if (!getConfirmarSenha().equals(usuario.getSenha())) {
			setMensagemDeErro("Confirmação de Senha inválida!");
			return null;
		}
		
		usuarioFachada.salvar(usuario);
		return "ambiente_usuario";
	}
	
	public String editar() {
		setMensagemDeErro("");
		return "editar_usuario";
	}
	
	public String salvar() {
		setMensagemDeErro("");
		
		if (!getConfirmarSenha().equals(usuario.getSenha())) {
			setMensagemDeErro("Confirmação de Senha inválida!");
			return null;
		}
		
		usuarioFachada.salvar(usuario);
		//limpar_usuario();
		return "voltar_ambiente_usuario";
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getMensagemDeErro() {
		return mensagemDeErro;
	}

	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}
	
}