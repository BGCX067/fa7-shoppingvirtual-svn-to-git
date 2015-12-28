/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.controle;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.anuncia.negocio.AdministradorFachada;
import br.com.anuncia.negocio.modelo.Evento;
import br.com.anuncia.negocio.modelo.TipoEvento;

public class ManterTiposdeEventosBean {

	// Objetos utilizados pela página
	private Long id;
	private TipoEvento tipodeevento;
	private List<TipoEvento> tiposdeeventos;
	
	// Objetos para acesso ao negócio
	private AdministradorFachada administradorFachada;
	
	public ManterTiposdeEventosBean() {
		// TODO Auto-generated constructor stub
		administradorFachada = new AdministradorFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (tipodeevento == null) {
			tipodeevento = new TipoEvento();
		}
		
		if (administradorFachada == null) {
			administradorFachada = new AdministradorFachada();
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
	 * @return Returns the tipodeevento.
	 */
	public TipoEvento getTipodeevento() {
		return tipodeevento;
	}

	/**
	 * @param tipodeevento The tipodeevento to set.
	 */
	public void setTipodeevento(TipoEvento tipodeevento) {
		this.tipodeevento = tipodeevento;
	}
	
	/**
	 * @return Returns the tiposdeeventos.
	 */
	public List<TipoEvento> getTiposdeeventos() {
		return tiposdeeventos;
	}

	/**
	 * @param tiposdeeventos The clientes to set.
	 */
	public void setTiposdeeventos(List<TipoEvento> tiposdeeventos) {
		this.tiposdeeventos = tiposdeeventos;
	}
	
	private void limpar_tipodeevento() {
		tipodeevento = new TipoEvento();
	}
	
	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}
	
	public String pesquisar() {
		tiposdeeventos = administradorFachada.recuperarTipoEventosPorCodigoOuNome(tipodeevento);
		return null;
	}
	
	public String incluir() {
		limpar_tipodeevento();	
		return "editar";
	}
		
	public String editar() {
		
		pegaParametroId();
		
		if ((getId() != null) && (getId().longValue() > 0))
			tipodeevento = administradorFachada.recuperarTipoEvento(getId());
		
		return "editar";
	}
	
	public String salvar() {
		administradorFachada.salvar(tipodeevento);
		limpar_tipodeevento();
		return "voltar";
	}
	
	   public String excluir() {
			
	    	pegaParametroId();
	    	
	    	TipoEvento tipo_evento_temp = tipodeevento;
	    	
			if ((getId() != null) && (getId().longValue() > 0))
				tipodeevento = administradorFachada.recuperarTipoEvento(getId());
			
			administradorFachada.excluir(tipodeevento);
			
			tipodeevento = tipo_evento_temp;
			
			pesquisar();

			return null;
		}
}