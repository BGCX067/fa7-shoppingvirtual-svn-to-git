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

import br.com.anuncia.negocio.AdministradorFachada;
import br.com.anuncia.negocio.modelo.Administrador;


public class ManterAdministradorBean {

	// Objetos utilizados pela página
	private Long id;
	private Administrador administrador;
	
	// Objetos para acesso ao negócio
	private AdministradorFachada administradorFachada;
	
	public ManterAdministradorBean() {
		// TODO Auto-generated constructor stub
		administradorFachada = new AdministradorFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (administrador == null) {
			administrador = new Administrador();
		}
		
		if (administradorFachada == null) {
			administradorFachada = new AdministradorFachada();
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
	public Administrador getAdministrador() {
		return administrador;
	}

	/**
	 * @param usuario The usuario to set.
	 */
	public void setAdministrador(Administrador usuario) {
		this.administrador = usuario;
	}
	
	private void limpar_administrador() {
		administrador = new Administrador();
	}
	
	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}
	
	public String salvar() {
		//administradorFachada.salvar(administrador);
		//limpar_usuario();
		return "voltar_ambiente_administrador";
	}
	
}