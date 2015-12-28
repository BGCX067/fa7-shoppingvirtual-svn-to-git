/*
// * Created on 07/10/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.controle;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.anuncia.negocio.AdministradorFachada;
import br.com.anuncia.negocio.AnuncianteFachada;
import br.com.anuncia.negocio.modelo.Anunciante;


public class GerenciarAnunciantesBean {

	// Objetos utilizados pela página
	private Long id;
	private Anunciante anunciante;
	private List<Anunciante> anunciantes;
	private String status;
	
	// Objetos para acesso ao negócio
	private AnuncianteFachada anuncianteFachada;
	private AdministradorFachada administradorFachada;
	
	public GerenciarAnunciantesBean() {
		// TODO Auto-generated constructor stub
		administradorFachada = new AdministradorFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (anunciante == null) {
			anunciante = new Anunciante();
		}
		
		if (administradorFachada == null) {
			administradorFachada = new AdministradorFachada();
		}
		
		if (anuncianteFachada == null)
			anuncianteFachada = new AnuncianteFachada();
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
	 * @return Returns the evento.
	 */
	public Anunciante getAnunciante() {
		return anunciante;
	}

	/**
	 * @param evento The evento to set.
	 */
	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}
	
	/**
	 * @return Returns the clientes.
	 */
	public List<Anunciante> getAnunciantes() {
		return anunciantes;
	}

	/**
	 * @param evento The evento to set.
	 */
	public void setEventos(List<Anunciante> anunciantes) {
		this.anunciantes = anunciantes;
	}
	
	private void limparAnunciante() {
		anunciante = new Anunciante();
	}
	
	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}
	
	public String pesquisar() {
		anunciante.setStatus(status);
		anunciantes = administradorFachada.recuperarAnunciantesPorCodigoOuNome(anunciante);	
		return null;
	}

	public String gerenciar() {
		
		pegaParametroId();
		
		if ((getId() != null) && (getId().longValue() > 0))
			anunciante = anuncianteFachada.recuperarAnunciante(getId()); //administradorFachada.recuperarAnunciantePorCodigo(getId());
		
		return "gerenciar";
	}
	
	public String rejeitar() {	
		anunciante.setStatus("r");
		anunciante.setDataStatus(new Date());

		anuncianteFachada.salvar(anunciante);
		limparAnunciante();
		return "voltar";
	}
	
	public String aceitar() {		
		anunciante.setStatus("a");
		anunciante.setDataStatus(new Date());
		
		anuncianteFachada.salvar(anunciante);
		limparAnunciante();
		return "voltar";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSituacao()
	{
		if (anunciante.getStatus() != null && "a".equals(anunciante.getStatus().trim()))
			return "Aceito";
		else if (anunciante.getStatus() != null && "r".equals(anunciante.getStatus().trim()))
			return "Rejeitado";
		else
			return "Não avaliado";
	}
	public Boolean getDisabledAceitar()
	{
		return (anunciante.getStatus() != null && anunciante.getStatus().trim().equals("a"));
	}
	public Boolean getDisabledRejeitar()
	{
		return (anunciante.getStatus() != null && anunciante.getStatus().trim().equals("r"));	
	}
}