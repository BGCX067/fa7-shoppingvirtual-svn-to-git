/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.negocio;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import com.sun.corba.se.pept.protocol.MessageMediator;

import br.com.anuncia.exceptions.ExcecaoNegocio;
import br.com.anuncia.negocio.modelo.Anunciante;
import br.com.anuncia.negocio.modelo.AreaProfissional;
import br.com.anuncia.negocio.modelo.Evento;
import br.com.anuncia.negocio.modelo.MensagemEvento;
import br.com.anuncia.negocio.modelo.Preco;
import br.com.anuncia.negocio.modelo.Secao;
import br.com.anuncia.negocio.modelo.TipoEvento;
import br.com.anuncia.negocio.modelo.Usuario;
import br.com.anuncia.negocio.persistencia.AnuncianteDAO;
import br.com.anuncia.negocio.persistencia.AreaProfissionalDAO;
import br.com.anuncia.negocio.persistencia.AvaliacaoDAO;
import br.com.anuncia.negocio.persistencia.EventoDAO;
import br.com.anuncia.negocio.persistencia.PrecoDAO;
import br.com.anuncia.negocio.persistencia.SecaoDAO;
import br.com.anuncia.negocio.persistencia.TipoEventoDAO;
import br.com.anuncia.negocio.persistencia.UsuarioDAO;
import br.com.anuncia.util.SendMail;
import br.com.anuncia.util.SendMailUsingGmail;

public class AdministradorFachada {

	private SecaoDAO secaoDAO;
	private AreaProfissionalDAO areaProfissionalDAO;
	private AnuncianteDAO anuncianteDAO;
	private TipoEventoDAO tipoEventoDAO;
	private EventoDAO eventoDAO;
	private UsuarioDAO usuarioDAO;
	private PrecoDAO precoDAO;
	private AvaliacaoDAO avaliacaoDAO;

	public AdministradorFachada() {
		// TODO Auto-generated constructor stub
		secaoDAO = new SecaoDAO();
		areaProfissionalDAO = new AreaProfissionalDAO();
		anuncianteDAO = new AnuncianteDAO();
		tipoEventoDAO = new TipoEventoDAO();
		eventoDAO = new EventoDAO();
		usuarioDAO = new UsuarioDAO();
		precoDAO = new PrecoDAO();
		avaliacaoDAO = new AvaliacaoDAO();
	}

	public List<Secao> recuperarSecoesPorCodigoOuNome(Secao secao) {
		return secaoDAO.recuperarPorCodigoOuNome(secao);
	}

	public Secao recuperarSecao(Long id) {
		return secaoDAO.findById(id);
	}

	public void salvar(Secao secao) {
		secaoDAO.save(secao);
	}

	public List<AreaProfissional> recuperarAreasProfissionaisPorCodigoOuNome(
			AreaProfissional areaProfissional) {
		return areaProfissionalDAO.recuperarPorCodigoOuNome(areaProfissional);
	}

	public AreaProfissional recuperarAreaProfissional(Long id) {
		return areaProfissionalDAO.findById(id);
	}

	public void validarAreaProfissional(AreaProfissional areaProfissional)
			throws ExcecaoNegocio {

		if (areaProfissional.getId() == null) {
			List<AreaProfissional> lista = (List<AreaProfissional>) areaProfissionalDAO.recuperarPorNomeExato(areaProfissional);
			if (lista != null && lista.size() > 0)
				throw new ExcecaoNegocio(
						"Nome da Área Profissional deve ser único");
		}
	}

	public void salvar(AreaProfissional areaProfissional) {
		areaProfissionalDAO.save(areaProfissional);
	}

	public void excluir(AreaProfissional areaProfissional) {
		areaProfissionalDAO.remove(areaProfissional);

	}

	public void excluir(Secao secao) {
		secaoDAO.remove(secao);

	}

	public List<Secao> listarTodasSecoes() {

		List<Secao> secoes = secaoDAO.findAll();
		return secoes;
	}

	public List<TipoEvento> recuperarTipoEventosPorCodigoOuNome(
			TipoEvento tipoEvento) {
		return tipoEventoDAO.recuperarPorCodigoOuNome(tipoEvento);
	}

	public TipoEvento recuperarTipoEvento(Long id) {
		return tipoEventoDAO.findById(id);
	}

	public void salvar(TipoEvento tipoEvento) {
		tipoEventoDAO.save(tipoEvento);
	}

	public List<Anunciante> recuperarAnunciantesPorCodigoOuNome(
			Anunciante anunciante) {
		return anuncianteDAO.recuperarPorCodigoOuNome(anunciante);
	}

	public Anunciante recuperarAnunciantePorCodigo(Long id) {
		return anuncianteDAO.findById(id);
	}	
	

	public List<Evento> listarTodosEventos() {

		List<Evento> eventos = eventoDAO.findAll();
		return eventos;
	}

	public void gerarMala(MensagemEvento mensagemEvento) {
		SendMail sendMail = new SendMail();
		List<Usuario> usuarios = usuarioDAO.findAll();
		if (usuarios != null && usuarios.size() > 0) {
			String[] emailsUsuarios = new String[usuarios.size()];
			int i = 0;
			for (Usuario usuario : usuarios) {
				emailsUsuarios[i++] = usuario.getEmail();
			}
			try {
				SendMailUsingGmail.sendSSLMessage(emailsUsuarios, "Anuncia7", mensagemEvento.getMensagem());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void excluir(TipoEvento tipoevento) {
		tipoEventoDAO.remove(tipoevento);
	}

	public void salvar(Preco preco) {
		precoDAO.save(preco);
	}

	public Preco recuperarPreco(Long id) {
		// TODO Auto-generated method stub
		return precoDAO.findById(id);
	}

	public List<Preco> recuperarPrecosPorSecao(Long idSecao) {
		return precoDAO.recuperarPorSecao(idSecao);
	}

	//EXCLUIR PREÇO
	public void excluir(Preco preco) {
		precoDAO.remove(preco);

	}
	
	
	//verifica se já existe a seção
	public void validarSecao(Secao secao) throws ExcecaoNegocio{
		
		if(secao.getId()== null){
			List<Secao> lista = (List<Secao>) secaoDAO.recuperarPorCodigoOuNome(secao);
			if(lista!=null && lista.size()> 0)
				throw new ExcecaoNegocio("Nome da seção já existe!");
		}
	}
	//secao
	public boolean existeEventosComSecao(Secao sec)
	{
		return eventoDAO.existeEventoComSecao(sec);
	}
	


}
