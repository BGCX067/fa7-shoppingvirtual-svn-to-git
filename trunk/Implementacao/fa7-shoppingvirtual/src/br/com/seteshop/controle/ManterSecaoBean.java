/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.seteshop.controle;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.util.ArrayList;

import br.com.seteshop.negocio.SecaoFachada;
import br.com.seteshop.negocio.modelo.Loja;
import br.com.seteshop.negocio.modelo.Secao;

public class ManterSecaoBean {

	//Objetos utilizados pela pagina
	private Long id;
	private Secao secao;
	private List<Secao> secoes;
	
	//Objetos para acesso ao negocio
	private SecaoFachada secaoFachada;
	
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

	public ManterSecaoBean() {
		// TODO Auto-generated constructor stub
		secaoFachada = new SecaoFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (secao == null) {
			secao = new Secao();
		}
		
		if (secaoFachada == null) {
			secaoFachada = new SecaoFachada();
		}
		
		if( secoes == null ){
			secoes = new ArrayList<Secao>();
		}
		
		secoes = secaoFachada.findAll();
		
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
	 * @return Returns the secoes.
	 */
	public List<Secao> getSecoes() {
		return secoes;
	}

	/**
	 * @param secao
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
		secoes = secaoFachada.recuperarSecoesPorCodigoOuNome(secao);	
		return null;
	}
	
	public String incluir() {
		limpar_secao();	
		return "editar";
	}
		
	public String editar() {
		
		pegaParametroId();
		
		if ((getId() != null) && (getId().longValue() > 0))
			secao = secaoFachada.recuperarSecao(getId());
		
		return "editar";
	}
	
	public String salvar() {
		try {
			secaoFachada.validarSecao(secao);
			secaoFachada.salvar(secao);
			limpar_secao();
			secoes = secaoFachada.recuperarSecoesPorCodigoOuNome(secao);	
			mensagemDeErro="";
			return "voltar";
		} catch (Exception e) {
			mensagemDeErro = e.getMessage();
			return null;
		}
	}
	

	
	public String excluir() {
		
    	pegaParametroId();
    	
    	Secao secao_temp = secao;
    	
		if ((getId() != null) && (getId().longValue() > 0))
			secao = secaoFachada.recuperarSecao(getId());
		secaoFachada.excluir(secao);
		limpar_secao();

		/*
		if (!anuncianteFachada.exiteEventosComDepartamento(departamento)) 
		{
			administradorFachada.excluir(departamento);
			departamento = area_profissional_temp;
			pesquisar();
		} else {
			mensagemDeErro = "Área Profissional não pode ser excluída, por que existe(m) evento(s) associado(s)";
		}
		*/
		secoes = secaoFachada.recuperarSecoesPorCodigoOuNome(secao);	
		return null;
	}
}
