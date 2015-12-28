/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.seteshop.negocio;

import java.util.List;

import br.com.seteshop.negocio.modelo.Cliente;
import br.com.seteshop.negocio.modelo.Usuario;
import br.com.seteshop.negocio.persistencia.ClienteDAO;
import br.com.seteshop.negocio.persistencia.UsuarioDAO;

public class UsuarioFachada {

	private ClienteDAO clienteDAO;

	private UsuarioDAO usuarioDAO;

	public UsuarioFachada() {
		// TODO Auto-generated constructor stub
		clienteDAO = new ClienteDAO();
		usuarioDAO = new UsuarioDAO();
		
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

	public Usuario recuperarUsuarioPorLogin(String login) {
		return usuarioDAO.recuperaPorLogin(login);

	}
	
	public Usuario recuperarUsuarioPorEmailESenha(String email, String senha) {
		return usuarioDAO.recuperaPorLoginESenha(email, senha);
	}

}
