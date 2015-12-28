/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.seteshop.negocio;

import br.com.seteshop.exceptions.ExcecaoNegocio;
import br.com.seteshop.negocio.modelo.Cliente;
import br.com.seteshop.negocio.persistencia.AdministradorDAO;
import br.com.seteshop.negocio.persistencia.ClienteDAO;

public class LoginFachada {

	private ClienteDAO clienteDAO;

	
	private AdministradorDAO administradorDAO;
	
	public LoginFachada() {
		// TODO Auto-generated constructor stub
		clienteDAO = new ClienteDAO();
		//usuarioDAO = new UsuarioDAO();
		administradorDAO = new AdministradorDAO();
	}

	//public List<Cliente> recuperarClientePorCodigoOuNome(Cliente cliente) {
	//	return clienteDAO.recuperarPorCodigoOuNome(cliente);
	//}

	//public Cliente recuperarCliente(Long id) {
	//	return clienteDAO.findById(id);
	//}
	
	//public void excluir(Cliente cliente) {
	//	clienteDAO.remove(cliente);
	//}

	//public void salvar(Cliente cliente) {
	//	clienteDAO.save(cliente);
	//}
	
	public Cliente recuperarCliente(Long id) {
		return clienteDAO.findById(id);
	}
	
	public void salvar(Cliente cliente) {
		clienteDAO.save(cliente);
	}

	public Object login(String login, String senha, String perfil) throws Exception {
		
		Object objeto = null;
		
	/*	try {
			
			if (perfil.equals("anunciante")) {
				objeto = anuncianteDAO.recuperaPorLoginESenha(login, senha);
			} else {
				if(perfil.equals("cliente")) {
					objeto = clienteDAO.recuperaPorLoginESenha(login, senha);
				} else {
					if (perfil.equals("administrador")) {
						objeto = administradorDAO.recuperaPorLoginESenha(login, senha);
					}
				}
			}
		} 
		
		catch (Exception e) {
			String msg = null;
			if (perfil.equals("anunciante")) {
				msg = "Anunciante nao cadastrado ou senha incorreta, tente novamente";
			} else {
				if(perfil.equals("cliente")) {
					msg = "Cliente nao cadastrado ou senha incorreta, tente novamente";
				} else {
					if (perfil.equals("administrador")) {
						msg = "Administrador nao cadastrado ou senha incorreta, tente novamente";
					}
				}
			}
			throw new ExcecaoNegocio(msg);
		}
		*/
		return objeto;
	}
}
