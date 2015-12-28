/*
 * Created on 08/12/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.anuncia.negocio.AdministradorFachada;
import br.com.anuncia.negocio.modelo.Evento;
import br.com.anuncia.negocio.modelo.Preco;
import br.com.anuncia.negocio.modelo.Secao;


public class ManterPrecoBean {
	// Objetos utilizados pela página
	private Secao secao;
	private List<Secao> secoes;
	private Long idSecao;
	private String mensagemDeErro;
	private Long id;
	private Preco preco;
	private List<Preco> precos;

	

	private AdministradorFachada administradorFacahada;

	public ManterPrecoBean() {
		// TODO Auto-generated constructor stub
		administradorFacahada = new AdministradorFachada();
	}

	public Long getIdSecao() {
		return idSecao;
	}

	public void setIdSecao(Long idSecao) {
		this.idSecao = idSecao;
	}

	@PostConstruct
	public void init() {

		if (secao == null) {
			secao = new Secao();
		}

		if (administradorFacahada == null) {
			administradorFacahada = new AdministradorFachada();
		}
		if (secoes == null) {
			secoes = administradorFacahada.listarTodasSecoes();
		}
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public List<Secao> getSecoes() {
		return administradorFacahada.listarTodasSecoes();
	}

	public void setSecoes(List<Secao> secoes) {
		this.secoes = secoes;
	}

	private void limpar_secao() {
		preco = new Preco();
	}

	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}

	//PESQUISAR
	public String pesquisar() {
		precos = new ArrayList<Preco>();
		precos = administradorFacahada.recuperarPrecosPorSecao(idSecao);
		return null;
	}

	//INCLUIR
	public String incluir() {
		mensagemDeErro=null;
		limpar_secao();
		
		Secao sec;
		for (int i = 0; i < secoes.size(); i++) {
			sec = (Secao) secoes.get(i);
			if (sec.getId() == idSecao) {
				secao = sec;
			}
		}
		
		return "editar";
	}

	//EDITAR
	public String editar() {

		pegaParametroId();

		if ((getId() != null) && (getId().longValue() > 0))
			preco = administradorFacahada.recuperarPreco(getId());
		return "editar";
	}

	//SALVAR
	public String salvar() {
		Date dataInicio = preco.getDataInicio();
		Date dataFim = preco.getDataFim();
		
		if(dataInicio.after(dataFim)){
			mensagemDeErro = "Data inválida, tente de novo!";
			return null;
		}
		else{
			
			getSecao().setId(idSecao);
			preco.setSecao(getSecao());
			administradorFacahada.salvar(preco);
			limpar_secao();
			return "voltar";
		}
	}
	
	

	public List<Preco> getPrecos() {
		return precos;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}

	public Preco getPreco() {
		return preco;
	}

	public void setPreco(Preco preco) {
		this.preco = preco;
	}

	//EXCLUIR
	public String excluir() {

		pegaParametroId();

		if ((getId() != null) && (getId().longValue() > 0))
			preco = administradorFacahada.recuperarPreco(getId());

		administradorFacahada.excluir(preco);

		pesquisar();

		return null;
		
	}

	public final String getMensagemDeErro() {
		return mensagemDeErro;
	}

	public final void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}
}
