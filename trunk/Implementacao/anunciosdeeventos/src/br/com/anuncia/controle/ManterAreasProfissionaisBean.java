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
import br.com.anuncia.negocio.modelo.AreaProfissional;

public class ManterAreasProfissionaisBean {

	// Objetos utilizados pela página
	private Long id;
	private AreaProfissional areaprofissional;
	private List<AreaProfissional> areasprofissionais;
	
	// Objetos para acesso ao negócio
	private AdministradorFachada administradorFachada;
	private AnuncianteFachada anuncianteFachada;
	
	private String mensagemDeErro;
	
	/**
	 * @return Returns the mensagemDeErro.
	 */
	public String getMensagemDeErro() {
		return mensagemDeErro;
	}

	/**
	 * @param mensagemDeErro The mensagemDeErro to set.
	 */
	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}

	public ManterAreasProfissionaisBean() {
		// TODO Auto-generated constructor stub
		administradorFachada = new AdministradorFachada();
		anuncianteFachada = new AnuncianteFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (areaprofissional == null) {
			areaprofissional = new AreaProfissional();
		}
		
		if (administradorFachada == null) {
			administradorFachada = new AdministradorFachada();
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
	 * @return Returns the areaprofissional.
	 */
	public AreaProfissional getAreaprofissional() {
		return areaprofissional;
	}

	/**
	 * @param areaprofissional The areaprofissional to set.
	 */
	public void setAreaprofissional(AreaProfissional areaprofissional) {
		this.areaprofissional = areaprofissional;
	}
	
	/**
	 * @return Returns the areasprofissionais.
	 */
	public List<AreaProfissional> getAreasprofissionais() {
		return areasprofissionais;
	}

	/**
	 * @param areasprofissionais The clientes to set.
	 */
	public void setAreasprofissionais(List<AreaProfissional> areasprofissionais) {
		this.areasprofissionais = areasprofissionais;
	}
	
	private void limpar_areaprofissional() {
		areaprofissional = new AreaProfissional();
	}
	
	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}
	
	public String pesquisar() {
		areasprofissionais = administradorFachada.recuperarAreasProfissionaisPorCodigoOuNome(areaprofissional);	
		return null;
	}
	
	public String incluir() {
		limpar_areaprofissional();	
		return "editar";
	}
		
	public String editar() {
		
		pegaParametroId();
		
		if ((getId() != null) && (getId().longValue() > 0))
			areaprofissional = administradorFachada.recuperarAreaProfissional(getId());
		
		return "editar";
	}
	
	public String salvar() {
		try {
			administradorFachada.validarAreaProfissional(areaprofissional);
			administradorFachada.salvar(areaprofissional);
			limpar_areaprofissional();
			return "voltar";
		} catch (Exception e) {
			mensagemDeErro = e.getMessage();
			return null;
		}
	}
	
	public String excluir() {
		
    	pegaParametroId();
    	
    	AreaProfissional area_profissional_temp = areaprofissional;
    	
		if ((getId() != null) && (getId().longValue() > 0))
			areaprofissional = administradorFachada.recuperarAreaProfissional(getId());

		if (!anuncianteFachada.exiteEventosComAreaProfissional(areaprofissional)) 
		{
			administradorFachada.excluir(areaprofissional);
			areaprofissional = area_profissional_temp;
			pesquisar();
		} else {
			mensagemDeErro = "Área Profissional não pode ser excluída, por que existe(m) evento(s) associado(s)";
		}
		
		return null;
	}
}