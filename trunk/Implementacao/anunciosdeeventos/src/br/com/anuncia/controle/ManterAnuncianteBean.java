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

import br.com.anuncia.negocio.AnuncianteFachada;
import br.com.anuncia.negocio.modelo.Anunciante;


public class ManterAnuncianteBean {

	// Objetos utilizados pela página
	private Long id;
	private Anunciante anunciante;
	
	// Objetos para acesso ao negócio
	private AnuncianteFachada anuncianteFachada;
	
	public ManterAnuncianteBean() {
		// TODO Auto-generated constructor stub
		anuncianteFachada = new AnuncianteFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (anunciante == null) {
			anunciante = new Anunciante();
		}
		
		if (anuncianteFachada == null) {
			anuncianteFachada = new AnuncianteFachada();
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
	 * @return Returns the anunciante.
	 */
	public Anunciante getAnunciante() {
		return anunciante;
	}

	/**
	 * @param anunciante The anunciante to set.
	 */
	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}
	
	private void limpar_anunciante() {
		anunciante = new Anunciante();
	}
	
	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}
	
	public String incluir() {
		//setMensagemDeErro("");
		limpar_anunciante();
		return "incluir_anunciante";
	}

	public String salvar_novo() {
		anuncianteFachada.salvar(anunciante);
		//limpar_anunciante();
		return "ambiente_anunciante";
	}
	
	public String salvar() {
		anuncianteFachada.salvar(anunciante);
		//limpar_anunciante();
		return "voltar_ambiente_anunciante";
	}
	
	//Método usado no caso de uso "Gerenciar anunciante".. (By FernandoVM)
	public String editar() {
		
		pegaParametroId();
		
		if ((getId() != null) && (getId().longValue() > 0)){
			anunciante = anuncianteFachada.recuperarAnunciante(getId());
		}
		return "editar";
	}
	
}