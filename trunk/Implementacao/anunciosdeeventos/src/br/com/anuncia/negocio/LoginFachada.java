/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.negocio;

import java.util.List;

import br.com.anuncia.exceptions.ExcecaoNegocio;
import br.com.anuncia.negocio.modelo.Cliente;
import br.com.anuncia.negocio.modelo.Usuario;
import br.com.anuncia.negocio.persistencia.AdministradorDAO;
import br.com.anuncia.negocio.persistencia.AnuncianteDAO;
import br.com.anuncia.negocio.persistencia.ClienteDAO;
import br.com.anuncia.negocio.persistencia.UsuarioDAO;

public class LoginFachada {

	private ClienteDAO clienteDAO;

	private AnuncianteDAO anuncianteDAO;
	private UsuarioDAO usuarioDAO;
	private AdministradorDAO administradorDAO;
	
	public LoginFachada() {
		// TODO Auto-generated constructor stub
		clienteDAO = new ClienteDAO();
		anuncianteDAO = new AnuncianteDAO();
		usuarioDAO = new UsuarioDAO();
		administradorDAO = new AdministradorDAO();
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
		usuarioDAO.save(usuario);
	}

	public Object login(String login, String senha, String perfil) throws Exception {
		
		Object objeto = null;
		
		try {
			
			if (perfil.equals("anunciante")) {
				objeto = anuncianteDAO.recuperaPorLoginESenha(login, senha);
			} else {
				if(perfil.equals("usuario")) {
					objeto = usuarioDAO.recuperaPorLoginESenha(login, senha);
				} else {
					if (perfil.equals("administrador")) {
						objeto = administradorDAO.recuperaPorLoginESenha(login, senha);
					}
				}
			}
		} catch (Exception e) {
			String msg = null;
			if (perfil.equals("anunciante")) {
				msg = "Anunciante não cadastrado ou senha incorreta, tente novamente";
			} else {
				if(perfil.equals("usuario")) {
					msg = "Usuário não cadastrado ou senha incorreta, tente novamente";
				} else {
					if (perfil.equals("administrador")) {
						msg = "Administrador não cadastrado ou senha incorreta, tente novamente";
					}
				}
			}
			throw new ExcecaoNegocio(msg);
		}
		
		return objeto;
	}
}
