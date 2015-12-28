/*
 * Created on 07/10/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.anuncia.negocio.AnuncianteFachada;
import br.com.anuncia.negocio.modelo.AreaProfissional;
import br.com.anuncia.negocio.modelo.Evento;
import br.com.anuncia.negocio.modelo.Secao;
import br.com.anuncia.negocio.modelo.TipoEvento;


public class ManterEventosBean {

	// Objetos utilizados pela página
	private Long id;
	private Evento evento;
	private List<Evento> eventos;
	
	private Long idTipoEvento;
	private Long idSecao;
	private Long idAreaProfissional;
	
	private List<TipoEvento> tiposEventos;
	private List<Secao> secoes;
	private List<AreaProfissional> areasProfissionais;
	
	// Objetos para acesso ao negócio
	private AnuncianteFachada anuncianteFachada;
	
	public ManterEventosBean() {
		// TODO Auto-generated constructor stub
		anuncianteFachada = new AnuncianteFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (evento == null) {
			evento = new Evento();
		}
		
		if (anuncianteFachada == null) {
			anuncianteFachada = new AnuncianteFachada();
		}
		
		tiposEventos = new ArrayList<TipoEvento>();
		secoes = new ArrayList<Secao>();
		areasProfissionais = new ArrayList<AreaProfissional>();
	
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
	 * @return Returns the evento.
	 */
	public Evento getEvento() {
		return evento;
	}

	/**
	 * @param evento The evento to set.
	 */
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	/**
	 * @return Returns the clientes.
	 */
	public List<Evento> getEventos() {
		return eventos;
	}

	/**
	 * @param evento The evento to set.
	 */
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
	private void limpar_evento() {
		evento = new Evento();
	}
	
	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}
	
	public String pesquisar() {
		eventos = anuncianteFachada.recuperarEventosPorCodigoOuNome(evento);
		if (eventos.size() == 0) {
			eventos = null;
		}
		return null;
	}
	
	public String incluir() {
		limpar_evento();	
		return "editar";
	}
		
	public String editar() {
		
		pegaParametroId();
		
		if ((getId() != null) && (getId().longValue() > 0)){
			evento = anuncianteFachada.recuperarEvento(getId());
		    idAreaProfissional = evento.getAreaProfissional().getId();
		    idSecao = evento.getSecao().getId();
		    idTipoEvento = evento.getTipoEvento().getId();
		}
		return "editar";
	}
	
	public String salvar() {
//		Secao secao = new Secao(1l);
//		TipoEvento tipoEvento = new TipoEvento(1l);
//		AreaProfissional areaProfissional = new AreaProfissional(1l);
		evento.setAreaProfissional(anuncianteFachada.recuperaAreaProfissionalPorId(idAreaProfissional));
		evento.setTipoEvento(anuncianteFachada.recuperaTipoEventoPorId(idTipoEvento));
		evento.setSecao(anuncianteFachada.recuperaSecaoPorId(idSecao));
		anuncianteFachada.salvar(evento);
		limpar_evento();
		return "voltar";
	}
	
    public String excluir() {
		
    	pegaParametroId();
    	
    	Evento evento_temp = evento;
    	
		if ((getId() != null) && (getId().longValue() > 0))
			evento = anuncianteFachada.recuperarEvento(getId());
		
		anuncianteFachada.excluir(evento);
		
		evento = evento_temp;
		
		pesquisar();

		return null;
	}

	/**
	 * @return Returns the tiposEventos.
	 */
	public List<TipoEvento> getTiposEventos() {
		tiposEventos = anuncianteFachada.listarTodosTiposEventos();
		return tiposEventos;
	}

	/**
	 * @return Returns the secoes.
	 */
	public List<Secao> getSecoes() {
		return anuncianteFachada.listarTodasSecoes();
	}

	/**
	 * @return Returns the areasProfissionais.
	 */
	public List<AreaProfissional> getAreasProfissionais() {
		areasProfissionais = anuncianteFachada.listarTodasAreasProfissionais();
		return areasProfissionais;
	}

	/**
	 * @return Returns the idTipoEvento.
	 */
	public Long getIdTipoEvento() {
		return idTipoEvento;
	}

	/**
	 * @param idTipoEvento The idTipoEvento to set.
	 */
	public void setIdTipoEvento(Long idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}

	/**
	 * @return Returns the idSecao.
	 */
	public Long getIdSecao() {
		return idSecao;
	}

	/**
	 * @param idSecao The idSecao to set.
	 */
	public void setIdSecao(Long idSecao) {
		this.idSecao = idSecao;
	}

	/**
	 * @return Returns the idAreaProfissional.
	 */
	public Long getIdAreaProfissional() {
		return idAreaProfissional;
	}

	/**
	 * @param idAreaProfissional The idAreaProfissional to set.
	 */
	public void setIdAreaProfissional(Long idAreaProfissional) {
		this.idAreaProfissional = idAreaProfissional;
	}

	/**
	 * @param tiposEventos The tiposEventos to set.
	 */
	public void setTiposEventos(List<TipoEvento> tiposEventos) {
		this.tiposEventos = tiposEventos;
	}

	/**
	 * @param secoes The secoes to set.
	 */
	public void setSecoes(List<Secao> secoes) {
		this.secoes = secoes;
	}

	/**
	 * @param areasProfissionais The areasProfissionais to set.
	 */
	public void setAreasProfissionais(List<AreaProfissional> areasProfissionais) {
		this.areasProfissionais = areasProfissionais;
	}
}