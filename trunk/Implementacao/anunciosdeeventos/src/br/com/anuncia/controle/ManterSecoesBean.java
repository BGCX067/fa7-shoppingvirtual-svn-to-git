/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.controle;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.anuncia.negocio.AdministradorFachada;
import br.com.anuncia.negocio.AnuncianteFachada;
import br.com.anuncia.negocio.modelo.Evento;
import br.com.anuncia.negocio.modelo.Secao;


public class ManterSecoesBean {

	// Objetos utilizados pela página
	private Long id;
	private Secao secao;
	private List<Secao> secoes;
	
	// Objetos para acesso ao negócio
	private AdministradorFachada administradorFachada;
	private AnuncianteFachada anuncianteFachada;
	
	private String mensagemDeErro;
	
	public String getMensagemDeErro() {
		return mensagemDeErro;
	}
	
	public void setMensagemDeErro(String mensagemDeErro)
	{
		this.mensagemDeErro = mensagemDeErro;
	}
	
	
	public ManterSecoesBean() {
		// TODO Auto-generated constructor stub
		administradorFachada = new AdministradorFachada();
		anuncianteFachada = new AnuncianteFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (secao == null) {
			secao = new Secao();
		}
		
		if (administradorFachada == null) {
			administradorFachada = new AdministradorFachada();
		}
		if (anuncianteFachada == null){
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
	 * @return Returns the secao.
	 */
	public Secao getSecao() {
		return secao;
	}

	/**
	 * @param secao The secao to set.
	 */
	public void setSecao(Secao secao) {
		this.secao = secao;
	}
	
	/**
	 * @return Returns the secaos.
	 */
	public List<Secao> getSecoes() {
		return secoes;
	}

	/**
	 * @param secoes The secoes to set.
	 */
	public void setSecoes(List<Secao> secoes) {
		this.secoes = secoes;
	}
	
	private void limpar_secao() {
		secao = new Secao();
	}
	
	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}
	
	public String pesquisar() {
		secoes = administradorFachada.recuperarSecoesPorCodigoOuNome(secao);			
		return null;
	}
	
	//incluir
	public String incluir() {
		limpar_secao();	
		return "editar";
	}
		
	//editar	
	public String editar() {		
		  pegaParametroId();		
		if ((getId() != null) && (getId().longValue() > 0)){
			secao = administradorFachada.recuperarSecao(getId());
		}
		return "editar";		
	}		
	
	// salvando
	public String salvar() {
		try {
			administradorFachada.validarSecao(secao);		
			administradorFachada.salvar(secao);			
			limpar_secao();
			return "voltar";			
		} catch (Exception e) {			
			mensagemDeErro = e.getMessage();
			return null;
		}		
	}		
	//EXCLUIR
	public String excluir() {		
    	pegaParametroId();
    	//Secao secao_temp = secao;    	
		
    	if ((getId() != null) && (getId().longValue() > 0))					
    		secao = administradorFachada.recuperarSecao(getId());
			if(!administradorFachada.existeEventosComSecao(secao))
			{
				administradorFachada.excluir(secao);
				//administradorFachada.excluir(secao);			
				//secao = secao_temp;
				limpar_secao();
				pesquisar();
			}
			else
			{				
				mensagemDeErro ="Secao nao pode ser excluida, pois existe(m) evento(s) associado(s) à seção!";
			}					
		return null;
	}		
}
 