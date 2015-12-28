/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.negocio;

import java.util.List;

import br.com.anuncia.negocio.modelo.Avaliacao;
import br.com.anuncia.negocio.modelo.Cliente;
import br.com.anuncia.negocio.modelo.Usuario;
import br.com.anuncia.negocio.persistencia.AvaliacaoDAO;
import br.com.anuncia.negocio.persistencia.ClienteDAO;
import br.com.anuncia.negocio.persistencia.UsuarioDAO;

public class UsuarioFachada {

	private ClienteDAO clienteDAO;

	private UsuarioDAO usuarioDAO;

	private AvaliacaoDAO avaliacaoDAO;
	
	

	public UsuarioFachada() {
		// TODO Auto-generated constructor stub
		clienteDAO = new ClienteDAO();
		usuarioDAO = new UsuarioDAO();
		avaliacaoDAO = new AvaliacaoDAO();
		
	}

	public List<Cliente> recuperarClientePorCodigoOuNome(Cliente cliente) {
		return clienteDAO.recuperarPorCodigoOuNome(cliente);
	}

	public Cliente recuperarCliente(Long id) {
		return clienteDAO.findById(id);
	}

	public void excluir(Cliente cliente) {
		clienteDAO.remove(cliente);
	}

	public void salvar(Cliente cliente) {
		clienteDAO.save(cliente);
	}

	public Usuario recuperarUsuario(Long id) {
		return usuarioDAO.findById(id);
	}

	public void salvar(Usuario usuario) {
		// usuarioDAO.save(usuario);
		if (usuario.getId() != null) {
			usuarioDAO.update(usuario);
		} else {
			usuarioDAO.save(usuario);
		}
	}

	public List<Avaliacao> listarAvaliacaoesPorIdUsuario(Long id) {
		List<Avaliacao> ava = avaliacaoDAO.listarAvaliacoesPorID(id);
		return ava;
	}

	public Avaliacao getAvaliacao(Long idEvento) {
		Avaliacao avaliacao = avaliacaoDAO.pegaAvaliacaoPorId(idEvento);
		return avaliacao;
	}

	public void salvarReavaliacao(Avaliacao avaliacao) {
		
		if (avaliacao.getId() != null) {
			avaliacaoDAO.update(avaliacao);			
		} else {
			avaliacaoDAO.save(avaliacao);
		}
	}

	public Usuario recuperarUsuarioPorLogin(String login) {
		return usuarioDAO.recuperaPorLogin(login);

	}
}
