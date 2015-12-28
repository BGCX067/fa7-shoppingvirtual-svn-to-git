/*
 * Created on 07/10/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.controle;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.anuncia.negocio.AnuncianteFachada;
import br.com.anuncia.negocio.modelo.Evento;
import br.com.anuncia.negocio.modelo.MensagemEvento;

public class ManterMensagemEventosBean {

	// Objetos utilizados pela página
	private Long id;
	private MensagemEvento mensagemEvento;
	private List<Evento> eventos;
	private Long idEvento;

	private List<MensagemEvento> mensagensEvento;

	private AnuncianteFachada anuncianteFachada;

	public ManterMensagemEventosBean() {
		// TODO Auto-generated constructor stub
		anuncianteFachada = new AnuncianteFachada();
	}

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	@PostConstruct
	public void init() {

		if (mensagemEvento == null) {
			mensagemEvento = new MensagemEvento();
		}

		if (anuncianteFachada == null) {
			anuncianteFachada = new AnuncianteFachada();
		}
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MensagemEvento getMensagemEvento() {
		return mensagemEvento;
	}

	public void setMensagemEvento(MensagemEvento mensagemEvento) {
		this.mensagemEvento = mensagemEvento;
	}

	public List<Evento> getEventos() {
		return anuncianteFachada.listarTodosEventos();
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	private void limpar_mensagemEvento() {
		mensagemEvento = new MensagemEvento();
	}

	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}

	public List<MensagemEvento> pesquisar() {
		return getMensagensEvento();
	}
	
	public List<MensagemEvento> getMensagensEvento() {
		return anuncianteFachada.listarTodasMensagemEventos(idEvento);
	}
	
	public void setMensagensEvento(List<MensagemEvento> mensagensEvento) {
		this.mensagensEvento = mensagensEvento;
	}
		
	public String incluir() {
		limpar_mensagemEvento();
		return "editar";
	}

	
	public String editar() {
		
		pegaParametroId();
		
		if ((getId() != null) && (getId().longValue() > 0))
			mensagemEvento = anuncianteFachada.recuperarMensagemEvento(getId());
	
		
		return "editar";
	}
	
	
	public String excluir() {
		
    	pegaParametroId();
    	
    	MensagemEvento mensagem_temp = mensagemEvento;
    	
		if ((getId() != null) && (getId().longValue() > 0))
			mensagemEvento = anuncianteFachada.recuperarMensagemEvento(getId());
		
		anuncianteFachada.excluir(mensagemEvento);
		
		mensagemEvento = mensagem_temp;
			

		return null;
	}

	public String salvar() {
		mensagemEvento.setEvento(anuncianteFachada.recuperarEvento(idEvento));
		anuncianteFachada.salvar(mensagemEvento);
		limpar_mensagemEvento();
		return "voltar";

	}
	//usando pelo caso de uso "Gerenciar Mensagens de Evento" By Alysson Bandeira 
	public String salvarMensagemGerenciar() {
		anuncianteFachada.salvar(mensagemEvento);
		limpar_mensagemEvento();
		return "voltar";

	}
	
}