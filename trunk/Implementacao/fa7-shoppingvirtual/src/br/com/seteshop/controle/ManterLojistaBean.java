/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.seteshop.controle;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.seteshop.negocio.LojistaFachada;
import br.com.seteshop.negocio.modelo.Lojista;
import br.com.seteshop.negocio.modelo.Usuario;


public class ManterLojistaBean {

	// Objetos utilizados pela p�gina
	private Long id;
	private Lojista lojista;
	private String confirmarSenha;
	private String mensagemDeErro;
	
	// Objetos para acesso ao neg�cio
	private LojistaFachada lojistaFachada;
	
	public ManterLojistaBean() {
		// TODO Auto-generated constructor stub
		lojistaFachada = new LojistaFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (lojista == null) {
			lojista = new Lojista();
		}
		
		if (confirmarSenha == null) {
			confirmarSenha = new String();
		}
		
		if (lojistaFachada == null) {
			lojistaFachada = new LojistaFachada();
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
	public Lojista getLojista() {
		return lojista;
	}

	/**
	 * @param usuario The usuario to set.
	 */
	public void setLojista(Lojista lojista) {
		this.lojista = lojista;
	}
	
	private void limpar_lojista() {
		lojista = new Lojista();
	}
	
	public String incluir() {
		setMensagemDeErro("");
		limpar_lojista();
		return "incluir_lojista";
	}
	
	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}

	public String salvar_novo() {
		setMensagemDeErro("");
		
		if (!getConfirmarSenha().equals(lojista.getSenha())) {
			setMensagemDeErro("Confirmacao de Senha invalida!");
			return null;
		}
		
		lojistaFachada.salvar(lojista);
		return "logoff";
	}
	
	public String editar() {
		setMensagemDeErro("");
		return "editar_lojista";
	}
	
	public String salvar() {
		setMensagemDeErro("");
		
		if (!getConfirmarSenha().equals(lojista.getSenha())) {
			setMensagemDeErro("Confirmacao de Senha invalida!");
			return null;
		}
		
		lojistaFachada.salvar(lojista);
		//limpar_usuario();
		return "logoff";
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
