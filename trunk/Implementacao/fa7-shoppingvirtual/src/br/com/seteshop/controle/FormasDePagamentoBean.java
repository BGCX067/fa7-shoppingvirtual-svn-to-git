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

import br.com.seteshop.negocio.FormasDePagamentoFachada;
import br.com.seteshop.negocio.modelo.FormasDePagamento;

public class FormasDePagamentoBean {

	//Objetos utilizados pela pagina
	private Long id;
	private FormasDePagamento formasDePagamento;
	private List<FormasDePagamento > formasDePagamentos = null;
	
	//Objetos para acesso ao negocio
	private FormasDePagamentoFachada formasDePagamentoFachada;
	
	private String mensagemDeErro;
	

	public String getMensagemDeErro() {
		return mensagemDeErro;
	}


	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}

	public FormasDePagamentoBean(){
		formasDePagamentoFachada = new FormasDePagamentoFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (formasDePagamento == null) {
			formasDePagamento = new FormasDePagamento();
		}
		
		if (formasDePagamentoFachada == null) {
			formasDePagamentoFachada = new FormasDePagamentoFachada();
		}
		
		//if( formasDePagamentos == null ){
		//	formasDePagamentos = new ArrayList<FormasDePagamento>();
		//}
		
		formasDePagamentos = formasDePagamentoFachada.findAll();
		
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
	 * @return Returns the formasDePagamento.
	 */
	public FormasDePagamento getFormasDePagamento() {
		return formasDePagamento;
	}

	/**
	 * @param formasDePagamento The FormasDePagamento to set.
	 */
	public void setFormasDePagamento(FormasDePagamento formasDePagamento) {
		this.formasDePagamento = formasDePagamento;
	}
	
	/**
	 * @return Returns the FormasDePagamento.
	 */
	public List<FormasDePagamento> getFormasDePagamentos(){
		return formasDePagamentos;
	}

	/**
	 * @param FormasDePagamento
	 */
	public void setFormasDePagamentos(List<FormasDePagamento> formasDePagamentos) {
		this.formasDePagamentos = formasDePagamentos;
	}
	
	private void limpar_formasDePagamento() {
		formasDePagamento = new FormasDePagamento();
	}
	
	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}
	
	public String pesquisar() {
		formasDePagamentos = formasDePagamentoFachada.recuperarPorCodigoOuNome(formasDePagamento);	
		return null;
	}
	
	public String incluir() {
		limpar_formasDePagamento();	
		return "editar";
	}
		
	public String editar() {
		
		pegaParametroId();
		
		if ((getId() != null) && (getId().longValue() > 0))
			formasDePagamento = formasDePagamentoFachada.recuperarFormasDePagamento(getId());
		
		return "editar";
	}
	
	public String salvar() {
		try {
			formasDePagamentoFachada.salvar(formasDePagamento);
			limpar_formasDePagamento();
			formasDePagamentos = formasDePagamentoFachada.recuperarPorCodigoOuNome(formasDePagamento);	
			mensagemDeErro="";
			return "voltar";
		} catch (Exception e) {
			mensagemDeErro = e.getMessage();
			return null;
		}
	}
	

	
	public String excluir() {
		
    	pegaParametroId();
    	
    	FormasDePagamento formasDePagamento_temp = formasDePagamento;
    	
		if ((getId() != null) && (getId().longValue() > 0))
			formasDePagamento = formasDePagamentoFachada.recuperarFormasDePagamento(getId());
		formasDePagamentoFachada.excluir(formasDePagamento);
		limpar_formasDePagamento();


		formasDePagamentos = formasDePagamentoFachada.recuperarPorCodigoOuNome(formasDePagamento);
		return null;
	}
}
