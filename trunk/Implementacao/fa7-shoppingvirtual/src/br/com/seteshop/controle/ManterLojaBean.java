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
import javax.faces.model.SelectItem;

import br.com.seteshop.negocio.LojaFachada;
import br.com.seteshop.negocio.SecaoFachada;
import br.com.seteshop.negocio.modelo.Endereco;
import br.com.seteshop.negocio.modelo.Loja;
import br.com.seteshop.negocio.modelo.Secao;
import br.com.seteshop.negocio.persistencia.SecaoDAO;

public class ManterLojaBean {

	// Objetos utilizados pela pagina
	private Long id;
	private Loja loja;
	private List<Loja> lojas;

	// Objetos para acesso ao negacio
	private LojaFachada lojaFachada;

	private String mensagemDeErro;
	
	private SecaoDAO secaoDAO;
	private SecaoFachada secaoFachada;
	private Long secaoId;

	/**
	 * @return Returns the mensagemDeErro.
	 */
	public String getMensagemDeErro() {
		return mensagemDeErro;
	}

	/**
	 * @param mensagemDeErro
	 *            The mensagemDeErro to set.
	 */
	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}

	public ManterLojaBean() {
		lojaFachada = new LojaFachada();
	}

	@PostConstruct
	public void init() {

		if (loja == null) {
			loja = new Loja();
			Endereco end = new Endereco();
			loja.setEndereco(end);
		}

		if (lojaFachada == null) {
			lojaFachada = new LojaFachada();
		}
		
		if (secaoFachada == null) {
			secaoFachada = new SecaoFachada();
		}

		if (lojas == null) {
			lojas = new ArrayList<Loja>();
		}

		lojas = lojaFachada.findAll();
	}

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Returns the loja.
	 */
	public Loja getLoja() {
		return loja;
	}

	/**
	 * @param loja
	 *            The loja to set.
	 */
	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	/**
	 * @return Returns the areasprofissionais.
	 */
	public List<Loja> getLojas() {
		if (lojas == null) {
			setLojas(new ArrayList<Loja>());
		}
		return lojas;
	}

	/**
	 * @param loja
	 */
	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}

	private void limpar_loja() {
		loja = new Loja();
		Endereco end = new Endereco();
		loja.setEndereco(end);
	}

	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}

	public String pesquisar() {
		lojas = lojaFachada.recuperarLojasPorCodigoOuNome(loja);
		return null;
	}

	public String incluir() {
		limpar_loja();
		return "editar";
	}

	public String editar() {

		pegaParametroId();

		if ((getId() != null) && (getId().longValue() > 0)) {
			loja = lojaFachada.recuperarLoja(getId());
			setLoja(loja);
			setSecaoId(loja.getSecao().getId());
		}	

		return "editar";
	}

	public String salvar() {
		try {
			Secao sec = secaoFachada.recuperarSecao(secaoId);
			loja.setSecao(sec);
			lojaFachada.validarLoja(loja);
			lojaFachada.salvar(loja);
			limpar_loja();
			return "voltar";
		} catch (Exception e) {
			mensagemDeErro = e.getMessage();
			return null;
		}
	}

	public String excluir() {

		pegaParametroId();

		Loja area_profissional_temp = loja;

		if ((getId() != null) && (getId().longValue() > 0))
			loja = lojaFachada.recuperarLoja(getId());
		lojaFachada.excluir(loja);
		limpar_loja();
		/*
		 * if (!anuncianteFachada.exiteEventosComLoja(loja)) {
		 * administradorFachada.excluir(loja); loja = area_profissional_temp;
		 * pesquisar(); } else { mensagemDeErro =
		 * "�rea Profissional n�o pode ser exclu�da, por que existe(m) evento(s) associado(s)"
		 * ; }
		 */
		return null;
	}
	
	public void setSecaoDAO (SecaoDAO secaoDAO) {
		this.secaoDAO = secaoDAO;
	}
	
	public List findSecao() {
		return secaoDAO.findAll();
	}

	private ArrayList<SelectItem> listaDeSecoes = null;
	
	public void setListaDeSecoes(ArrayList<SelectItem> listaDeSecoes) {
		this.listaDeSecoes = listaDeSecoes;
	}

	public ArrayList<SelectItem> getListaDeSecoes() {

		listaDeSecoes = new ArrayList<SelectItem>();

		List<Secao> secoes = secaoFachada.findAll();

		listaDeSecoes.add(new SelectItem("", "Selecione a Secao"));

		for (Secao secao : secoes) {
			listaDeSecoes.add(new SelectItem("" + secao.getId(),
					secao.getDescricao()));
		}

		return listaDeSecoes;
	}

	public void setSecaoId(Long secaoId) {
		this.secaoId = secaoId;
	}

	public Long getSecaoId() {
		return secaoId;
	}

}