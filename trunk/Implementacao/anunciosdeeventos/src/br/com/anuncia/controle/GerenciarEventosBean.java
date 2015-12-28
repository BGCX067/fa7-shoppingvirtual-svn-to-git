/*
// * Created on 07/10/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.controle;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.anuncia.negocio.AnuncianteFachada;
import br.com.anuncia.negocio.modelo.Evento;


public class GerenciarEventosBean {

	// Objetos utilizados pela página
	private Long id;
	private Evento evento;
	private List<Evento> eventos;
	private String status;
	
	// Objetos para acesso ao negócio
	private AnuncianteFachada anuncianteFachada;
	
	public GerenciarEventosBean() {
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
		evento.setStatus(status);
		eventos = anuncianteFachada.recuperarEventosPorCodigoOuNome(evento);	
		return null;
	}

	public String gerenciar() {
		
		pegaParametroId();
		
		if ((getId() != null) && (getId().longValue() > 0))
			evento = anuncianteFachada.recuperarEvento(getId());
		
		return "gerenciar";
	}
	
	public String rejeitar() {	
		evento.setStatus("r");
		evento.setDataStatus(new Date());

		anuncianteFachada.salvar(evento);
		limpar_evento();
		return "voltar";
	}
	
	public String aceitar() {		
		evento.setStatus("a");
		evento.setDataStatus(new Date());
		
		anuncianteFachada.salvar(evento);
		limpar_evento();
		return "voltar";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSituacao()
	{
		if (evento.getStatus() != null && "a".equals(evento.getStatus().trim()))
			return "Aceito";
		else if (evento.getStatus() != null && "r".equals(evento.getStatus().trim()))
			return "Rejeitado";
		else
			return "Não avaliado";
	}
	public Boolean getDisabledAceitar()
	{
		return (evento.getStatus() != null && evento.getStatus().trim().equals("a"));
	}
	public Boolean getDisabledRejeitar()
	{
		return (evento.getStatus() != null && evento.getStatus().trim().equals("r"));	
	}
}