package br.com.anuncia.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.anuncia.exceptions.EventoException;
import br.com.anuncia.negocio.AnuncianteFachada;
import br.com.anuncia.negocio.UsuarioFachada;
import br.com.anuncia.negocio.modelo.Avaliacao;
import br.com.anuncia.negocio.modelo.Evento;
import br.com.anuncia.negocio.modelo.Usuario;

public class ConsultarEventosBean {
	private Long id;
	private String pesquisa;
	private List<Evento> eventos = new ArrayList<Evento>();
	private Evento evento;
	private AnuncianteFachada anuncianteFachada;
	private String mensagem;
	private Long idTipoEvento;
	private Long idSecao;
	private Long idAreaProfissional;
	private Integer nota;
	private UsuarioFachada usuarioFachada;

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public Long getIdTipoEvento() {
		return idTipoEvento;
	}

	public void setIdTipoEvento(Long idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}

	public Long getIdSecao() {
		return idSecao;
	}

	public void setIdSecao(Long idSecao) {
		this.idSecao = idSecao;
	}

	public Long getIdAreaProfissional() {
		return idAreaProfissional;
	}

	public void setIdAreaProfissional(Long idAreaProfissional) {
		this.idAreaProfissional = idAreaProfissional;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@PostConstruct
	public void init() {

		if (evento == null) {
			evento = new Evento();
		}

		if (anuncianteFachada == null) {
			anuncianteFachada = new AnuncianteFachada();
		}
		if (usuarioFachada == null) {
			usuarioFachada = new UsuarioFachada();
		}

	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}

	public String pesquisar() {
		try {
			setEventos(anuncianteFachada.pesquisarEventos(getPesquisa()));
			setMensagem(getEventos().size() + " eventos encontrados.");
		} catch (EventoException e) {
			setMensagem(e.getMessage());
		} finally {
			return "resultadoConsulta";
		}

	}

	public String consultaAvancada() {
		try {
			setEventos(anuncianteFachada.pesquisarEventos(getPesquisa(),
					getIdTipoEvento(), getIdSecao(), getIdAreaProfissional()));
			setMensagem(getEventos().size() + " eventos encontrados.");
		} catch (EventoException e) {
			setMensagem(e.getMessage());
		} finally {
			return "resultadoConsulta";
		}

	}

	public String detalhar() {
		setMensagem("");
		pegaParametroId();
		setEvento(anuncianteFachada.pesquisaEventoPorId(getId()));
		anuncianteFachada.computarVisualizacaoDeEvento(getEvento());
		return "detalharEvento";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public boolean getHabilitaAvaliacao() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		if (session.getAttribute("login") != null) {
			
			return true;
		}
		return false;
	}

	public String avaliar() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		ManterUsuarioBean manterUsuarioBean = (ManterUsuarioBean) session.getAttribute("manterUsuarioBean");
		Usuario usuario = null;
		if (manterUsuarioBean != null) usuario = manterUsuarioBean.getUsuario();
		
		if (usuario.getId() != null) {
			
			Avaliacao avaliacao = anuncianteFachada.recuperarAvaliacaoPorUsuarioEvento(usuario, getEvento());
			
			if (avaliacao == null) {
				avaliacao = new Avaliacao();
			    avaliacao.setUsuario(usuario);
			    avaliacao.setEvento(getEvento());
			    avaliacao.setData(new Date());
			    avaliacao.setNota(getNota());
			    usuarioFachada.salvarReavaliacao(avaliacao);
			    setMensagem("Evento avaliado com Sucesso.");
			} else {
				setMensagem("Você já avaliou este evento. Para reavaliar um evento, selecione Reavaliar Eventos no menu do usuário.");
				return null;
			}
		} else {
			setMensagem("Você precisa estar logado no sistema como usuário para realizar uma avaliação.");
			return null;
		}
		return "success";
	}
}
