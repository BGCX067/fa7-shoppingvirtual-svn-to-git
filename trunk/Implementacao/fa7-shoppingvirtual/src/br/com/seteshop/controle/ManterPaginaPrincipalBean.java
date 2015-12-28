/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.seteshop.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.seteshop.exceptions.ProdutoException;
import br.com.seteshop.negocio.DepartamentoFachada;
import br.com.seteshop.negocio.LojaFachada;
import br.com.seteshop.negocio.ProdutoFachada;
import br.com.seteshop.negocio.SecaoFachada;
import br.com.seteshop.negocio.modelo.Departamento;
import br.com.seteshop.negocio.modelo.Loja;
import br.com.seteshop.negocio.modelo.Produto;
import br.com.seteshop.negocio.modelo.Secao;

public class ManterPaginaPrincipalBean {

	//Objetos utilizados pela pagina
	private Long idSecao;
	private Secao secao;
	private Departamento departamento;
	
	private Long idLoja;
	private Long idDepartamento;
	private Loja loja;
	private Produto produto;

	private List<Secao> secoes;
	private List<Departamento> departamentos;
	private List<Loja> lojas;
	private List<Produto> produtos;
	private List<Produto> produtos2;
	
	private List<Long> listaIdsDepartamentos;
	
	//Objetos para acesso ao negocio
	private SecaoFachada secaoFachada;
	private DepartamentoFachada departamentoFachada;
	private LojaFachada lojaFachada;
	private ProdutoFachada produtoFachada;
	
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

	public ManterPaginaPrincipalBean() {
		// TODO Auto-generated constructor stub
		secaoFachada = new SecaoFachada();
		departamentoFachada = new DepartamentoFachada();
		lojaFachada = new LojaFachada();
		produtoFachada = new ProdutoFachada();
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
		
		if (departamento == null) {
			departamento = new Departamento();
		}
		
		if (departamentoFachada == null) {
			departamentoFachada = new DepartamentoFachada();
		}
		
		if( departamentos == null ){
			departamentos = new ArrayList<Departamento>();
		}
		
		departamentos = departamentoFachada.findAll();
		
		if (loja == null) {
			loja = new Loja();
		}
		
		if (lojaFachada == null) {
			lojaFachada = new LojaFachada();
		}
		
		if( lojas == null ){
			lojas = new ArrayList<Loja>();
		}
		
		lojas = secoes.get(0).getLojas();
		
		if (produto == null) {
			produto = new Produto();
		}
		
		if (produtoFachada == null) {
			produtoFachada = new ProdutoFachada();
		}
		
		if( produtos == null ){
			produtos = new ArrayList<Produto>();
		}
		
		produtos = produtoFachada.findAll();
		
	}
	
	private void pegaParametroIdSecao() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		if(resquestParams.get("id") != null && !resquestParams.get("id").toString().isEmpty()){
			setIdSecao(Long.valueOf(resquestParams.get("id").toString()));
		}else{
			setIdSecao(new Long(0));
		}
	}
	
	private void pegaParametroIdLoja(){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		if(resquestParams.get("id") != null && !resquestParams.get("id").toString().isEmpty()){
			setIdLoja(Long.valueOf(resquestParams.get("id").toString()));
		}else{
			setIdLoja(new Long(0));
		}
	}
	
	private void pegaParametroIdDepartamento(){
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
        if(resquestParams.get("id") != null && !resquestParams.get("id").toString().isEmpty()){
            setIdDepartamento(Long.valueOf(resquestParams.get("id").toString()));
        }else{
            setIdDepartamento(new Long(0));
        }
    }

	public Long getIdSecao() {
		return idSecao;
	}

	public void setIdSecao(Long id) {
		this.idSecao = id;
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
	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}

	public SecaoFachada getSecaoFachada() {
		return secaoFachada;
	}

	public void setSecaoFachada(SecaoFachada secaoFachada) {
		this.secaoFachada = secaoFachada;
	}

	public DepartamentoFachada getDepartamentoFachada() {
		return departamentoFachada;
	}

	public void setDepartamentoFachada(DepartamentoFachada departamentoFachada) {
		this.departamentoFachada = departamentoFachada;
	}

	public LojaFachada getLojaFachada() {
		return lojaFachada;
	}

	public void setLojaFachada(LojaFachada lojaFachada) {
		this.lojaFachada = lojaFachada;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public ProdutoFachada getProdutoFachada() {
		return produtoFachada;
	}

	public void setProdutoFachada(ProdutoFachada produtoFachada) {
		this.produtoFachada = produtoFachada;
	}

	//Métodos 
	
	public String pesquisarSecao() {
		secoes = secaoFachada.recuperarSecoesPorCodigoOuNome(secao);	
		return null;
	}
	
	public List<Secao> getListarTodasSecoes() {
		secoes = secaoFachada.findAll();
		if ((secoes != null) && (!secoes.isEmpty()) && (secao == null))
			secao = secoes.get(0);
		return secoes;
	}
	
	public String recuperarLojasDaSecao() {
		pegaParametroIdSecao();
		
		if ((getIdSecao() != null) && (getIdSecao().longValue() > 0))
			secao = secaoFachada.recuperarSecao(getIdSecao());
		
		return null;
	}
	
	public String recuperarProdutosDaLoja() {
		pegaParametroIdLoja();
		setIdDepartamento(null);
		return null;
	}
	
	public String recuperarProdutosDoDepartamento() {
	    pegaParametroIdDepartamento();
	    setIdLoja(null);
        return null;
    }
	
	public String pesquisarDepartamento() {
		departamentos = departamentoFachada.recuperarDepartamentosPorCodigoOuNome(departamento);
		return null;
	}
	
	public List<Departamento> getListarTodosDepartamentos() {
		return departamentoFachada.findAll();
	}
	
	public String pesquisarLoja() {
		lojas = lojaFachada.recuperarLojasPorCodigoOuNome(loja);
		return null;
	}
	
	public List<Loja> getListarLojasDaSecao() {
		if(secao != null && secao.getLojas() != null)
			lojas = secao.getLojas();	
		return lojas;
	}
	
	public List<Produto> getListarProdutosDaLojaDepartamento() throws ProdutoException {
		if(getIdLoja() != null && getIdLoja().longValue() > 0) {
			produtos2 = produtoFachada.recuperarProdutosPorCodigoLoja(getIdLoja());
	    }else if (getIdDepartamento() != null && getIdDepartamento().longValue() > 0) {
	        produtos2 = produtoFachada.recuperarProdutosPorCodigoDepartamento(getIdDepartamento());
	    }
		return produtos2;
	}
	
	public String pesquisarProduto() {
		produtos = produtoFachada.recuperarProdutosPorCodigoOuNome(produto);
		return null;
	}
	
	public String getListarTodosProdutos() {
		produtos = produtoFachada.findAll();	
		return null;
	}
	
	public List<Produto> getListarTodosProdutosPrincipais() {
		produtos = produtoFachada.findAll();	
		return produtos;
	}

	public void setIdLoja(Long idLoja) {
		this.idLoja = idLoja;
	}

	public Long getIdLoja() {
		return idLoja;
	}

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

}
