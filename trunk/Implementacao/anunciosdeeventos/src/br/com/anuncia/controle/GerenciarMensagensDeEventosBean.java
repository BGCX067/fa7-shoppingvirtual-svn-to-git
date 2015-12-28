package br.com.anuncia.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.anuncia.negocio.AdministradorFachada;
import br.com.anuncia.negocio.AnuncianteFachada;
import br.com.anuncia.negocio.modelo.Evento;
import br.com.anuncia.negocio.modelo.MensagemEvento;

public class GerenciarMensagensDeEventosBean {
	private Long idEvento;
	private Long id;
	private List<Evento> eventos;
	private AdministradorFachada administradorFachada;
	private AnuncianteFachada anuncianteFachada;
	private List<MensagemEvento> mensagensEvento;
	private MensagemEvento mensagemEvento;
	private String status;

	@PostConstruct
	public void init() {

		if (administradorFachada == null) {
			administradorFachada = new AdministradorFachada();
		}
		if (anuncianteFachada == null) {
			anuncianteFachada = new AnuncianteFachada();
		}
	}

	@SuppressWarnings("unused")
	public String pesquisar() {
		if (idEvento != null) {
			mensagensEvento = null;
			mensagensEvento = anuncianteFachada.recuperarPorEventoEStatus(
					idEvento, getStatus());
		}
		return null;

	}

	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}

	public String gerenciar() {
		pegaParametroId();
		if ((getId() != null) && (getId().longValue() > 0))
			mensagemEvento = anuncianteFachada.recuperarMensagemEvento(getId());

		return "gerenciar";
	}

	public String rejeitar() {
		mensagemEvento.setStatus("r");
		mensagemEvento.setDataStatus(new Date());
		anuncianteFachada.salvar(mensagemEvento);
		return "voltar";
	}

	public String aceitar() {
		mensagemEvento.setStatus("a");
		mensagemEvento.setDataStatus(new Date());
		anuncianteFachada.salvar(mensagemEvento);
		return "voltar";
	}

	public String gerarMala() {
		administradorFachada.gerarMala(mensagemEvento);
		return "voltar";
	}

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public List<Evento> getEventos() {
		eventos = administradorFachada.listarTodosEventos();
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<MensagemEvento> getMensagensEvento() {
		return mensagensEvento;
	}

	public void setMensagensEvento(List<MensagemEvento> mensagemEvento) {
		this.mensagensEvento = mensagemEvento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSituacao() {
		if (mensagemEvento.getStatus() != null
				&& "a".equals(mensagemEvento.getStatus().trim()))
			return "Aceita";
		else if (mensagemEvento.getStatus() != null
				&& "r".equals(mensagemEvento.getStatus().trim()))
			return "Rejeitada";
		else
			return "Não avaliada";
	}

	public Boolean getDisabledAceitar() {
		return (mensagemEvento.getStatus() != null && mensagemEvento
				.getStatus().trim().equals("a"));
	}

	public Boolean getDisabledRejeitar() {
		return (mensagemEvento.getStatus() != null && mensagemEvento
				.getStatus().trim().equals("r"));
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

	private void limpar_mensagemEvento() {
		mensagemEvento = new MensagemEvento();
		mensagensEvento = new ArrayList<MensagemEvento>();
	}

	public String editar() {

		pegaParametroId();
        
		if ((getId() != null) && (getId().longValue() > 0))
			mensagemEvento = anuncianteFachada.recuperarMensagemEvento(getId());

		return "editar";
	}

	public String salvar() {
		anuncianteFachada.salvar(mensagemEvento);
		limpar_mensagemEvento();
		return "voltar";

	}

}
