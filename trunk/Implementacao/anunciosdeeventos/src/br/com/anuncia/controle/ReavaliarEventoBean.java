/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.controle;

//<<<<<<< .mine
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.anuncia.negocio.UsuarioFachada;
import br.com.anuncia.negocio.modelo.Avaliacao;
import br.com.anuncia.negocio.modelo.Usuario;


public class ReavaliarEventoBean {

	// Objetos utilizados pela página
	private Long idEvento;
	private Avaliacao avaliacao;
	private ManterUsuarioBean manterUsuarioBean;
	private Usuario usuario;
	private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
	
	
	// Objetos para acesso ao negócio
	private UsuarioFachada usuarioFachada;
	
	public ReavaliarEventoBean() {
		
		usuarioFachada = new UsuarioFachada();
	}
	
	@PostConstruct
	public void init() {
		if (avaliacao == null) {
			avaliacao = new Avaliacao();
		}
		
		if (usuarioFachada == null) {
			usuarioFachada = new UsuarioFachada();
		}
	}

	/**
	 * @return Returns the avaliacao.
	 */
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	/**
	 * @param usuario The avaliacao to set.
	 */
	public void setUsuario(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	private void limpar_avaliacao() {
		avaliacao = new Avaliacao();
	}
	
	private void pegaParametroIdEvento() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setIdEvento(Long.valueOf(resquestParams.get("idEvento").toString()));		
	}
	
	public void pegaUsuarioLogado(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
		.getExternalContext().getSession(false);
		this.manterUsuarioBean = (ManterUsuarioBean) session.getAttribute("manterUsuarioBean");
	}
	
	
	public String listarAvaliacoes(){
		pegaUsuarioLogado();
		setAvaliacoes(usuarioFachada.listarAvaliacaoesPorIdUsuario(this.manterUsuarioBean.getUsuario().getId()));
		return "listar_avaliacoes";
	}
	
	public String editarAvaliacao(){
		pegaParametroIdEvento();
		pegaUsuarioLogado();
		setUsuario(this.manterUsuarioBean.getUsuario());
		setAvaliacao(usuarioFachada.getAvaliacao(getIdEvento()));
		return "manter_avaliacao";
	}
	
	public String salvar() {
		usuarioFachada.salvarReavaliacao(avaliacao);
		return "voltar_avaliacao_listar";
	}

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public ManterUsuarioBean getManterUsuarioBean() {
		return manterUsuarioBean;
	}

	public void setManterUsuarioBean(ManterUsuarioBean manterUsuarioBean) {
		this.manterUsuarioBean = manterUsuarioBean;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}