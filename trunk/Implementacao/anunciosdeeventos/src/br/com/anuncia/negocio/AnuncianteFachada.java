/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.negocio;

import java.util.List;

import br.com.anuncia.exceptions.EventoException;
import br.com.anuncia.negocio.modelo.Anunciante;
import br.com.anuncia.negocio.modelo.AreaProfissional;
import br.com.anuncia.negocio.modelo.Avaliacao;
import br.com.anuncia.negocio.modelo.Evento;
import br.com.anuncia.negocio.modelo.MensagemEvento;
import br.com.anuncia.negocio.modelo.Secao;
import br.com.anuncia.negocio.modelo.TipoEvento;
import br.com.anuncia.negocio.modelo.Usuario;
import br.com.anuncia.negocio.persistencia.AnuncianteDAO;
import br.com.anuncia.negocio.persistencia.AreaProfissionalDAO;
import br.com.anuncia.negocio.persistencia.AvaliacaoDAO;
import br.com.anuncia.negocio.persistencia.EventoDAO;
import br.com.anuncia.negocio.persistencia.MensagemEventoDAO;
import br.com.anuncia.negocio.persistencia.SecaoDAO;
import br.com.anuncia.negocio.persistencia.TipoEventoDAO;

public class AnuncianteFachada {

	private EventoDAO eventoDAO;
	private AnuncianteDAO anuncianteDAO;
	private MensagemEventoDAO mensagemEventoDAO;
	private TipoEventoDAO tipoEventoDAO;
	private SecaoDAO secaoDAO;
	private AreaProfissionalDAO areaProfissionalDAO;
	private AvaliacaoDAO avaliacaoDAO;

	public AnuncianteFachada() {
		// TODO Auto-generated constructor stub
		eventoDAO = new EventoDAO();
		anuncianteDAO = new AnuncianteDAO();
		mensagemEventoDAO = new MensagemEventoDAO();
		secaoDAO = new SecaoDAO();
		tipoEventoDAO = new TipoEventoDAO();
		areaProfissionalDAO = new AreaProfissionalDAO();
		avaliacaoDAO = new AvaliacaoDAO();
	}

	public List<Evento> recuperarEventosPorCodigoOuNome(Evento evento) {
		return eventoDAO.recuperarPorCodigoOuNome(evento);
	}

	public Evento recuperarEvento(Long id) {
		return eventoDAO.findById(id);
	}

	public void salvar(Evento evento) {
		eventoDAO.save(evento);
	}

	public Anunciante recuperarAnunciante(Long id) {
		return anuncianteDAO.findById(id);
	}

	public void salvar(Anunciante anunciante) {
		// anuncianteDAO.save(anunciante);
		if (anunciante.getId() != null) {
			anuncianteDAO.update(anunciante);
		} else {
			anuncianteDAO.save(anunciante);
		}
	}

	public void salvar(MensagemEvento mensagemEvento) {
		mensagemEventoDAO.save(mensagemEvento);
	}

	public List<Evento> pesquisarEventos(String pesquisa)
			throws EventoException {
//		if (pesquisa != null && pesquisa.length() > 2) {
		if (pesquisa != null) {
			List<Evento> eventos = eventoDAO.pesquisarEventos(pesquisa);
			if (eventos == null || eventos.size() == 0) {
				throw new EventoException("Nenhuma retorno para sua busca");
			}
			return eventos;
		}
		throw new EventoException("Preencha o campo pesquisa");
	}

	public List<Evento> pesquisarEventos(String pesquisa, Long idTipoEvento,
			Long idSecao, Long idAreaProfissional) throws EventoException {
		if (pesquisa != null && pesquisa.length() > 2) {
			List<Evento> eventos = eventoDAO.pesquisarEventos(pesquisa,
					idTipoEvento, idSecao, idAreaProfissional);
			if (eventos == null || eventos.size() == 0) {
				throw new EventoException("Nenhuma retorno para sua busca");
			}
			return eventos;
		}
		throw new EventoException("Preencha o campo pesquisa");
	}

	public Evento pesquisaEventoPorId(Long id) {
		return eventoDAO.findById(id);
	}

	public void computarVisualizacaoDeEvento(Evento evento) {
		evento.setTotalDeVisualizacao(evento.getTotalDeVisualizacao() + 1);
		eventoDAO.save(evento);
	}

	public void excluir(Evento evento) {
		eventoDAO.remove(evento);
	}

	public List<Evento> listarTodosEventos() {

		List<Evento> eventos = eventoDAO.findAll();
		return eventos;
	}

	public List<MensagemEvento> listarTodasMensagemEventos(Long idEvento) {
		List<MensagemEvento> mensagens = mensagemEventoDAO
				.pesquisarMensagemEventos(idEvento);
		return mensagens;

	}

	public MensagemEvento recuperarMensagemEvento(Long id) {
		return mensagemEventoDAO.findById(id);
	}

	public void excluir(MensagemEvento mensagemEvento) {
		mensagemEventoDAO.remove(mensagemEvento);
	}

	public List<Secao> listarTodasSecoes() {

		List<Secao> secoes = secaoDAO.findAll();
		return secoes;
	}

	public List<AreaProfissional> listarTodasAreasProfissionais() {

		List<AreaProfissional> areasProfissionais = areaProfissionalDAO
				.findAll();
		return areasProfissionais;
	}

	public List<TipoEvento> listarTodosTiposEventos() {

		List<TipoEvento> tiposEventos = tipoEventoDAO.findAll();
		return tiposEventos;
	}

	public Secao recuperaSecaoPorId(Long id) {
		return secaoDAO.findById(id);
	}

	public TipoEvento recuperaTipoEventoPorId(Long id) {
		// TODO Auto-generated method stub
		return tipoEventoDAO.findById(id);
	}

	public AreaProfissional recuperaAreaProfissionalPorId(Long id) {
		// TODO Auto-generated method stub
		return areaProfissionalDAO.findById(id);
	}

	public List<MensagemEvento> recuperarPorEventoEStatus(Long idEvento,
			String status) {
		return mensagemEventoDAO.recuperarPorEventoEStatus(idEvento, status);
	}

	public boolean exiteEventosComAreaProfissional(AreaProfissional area) {

		return eventoDAO.exiteEventosComAreaProfissional(area);

	}
	
	public Avaliacao recuperarAvaliacaoPorUsuarioEvento(Usuario usuario, Evento evento) {
		
		return avaliacaoDAO.recuperarPorUsuarioEvento(usuario, evento);
	}
		
}