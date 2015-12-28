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

public class ClienteFachada {

	private ClienteDAO clienteDAO;	

	public ClienteFachada() {
		// TODO Auto-generated constructor stub
		clienteDAO = new ClienteDAO();		
	}
	
	public Cliente recuperarCliente(Long id) {
		return clienteDAO.findById(id);
	}

	public void salvar(Cliente cliente) {
		if (cliente.getId() != null) {
			clienteDAO.update(cliente);
		} else {
			clienteDAO.save(cliente);
		}
	}
	
	public Cliente recuperarClientePorLogin(String login) {
		return clienteDAO.recuperaPorLogin(login);

	}
	
	public Cliente recuperarClientePorEmailESenha(String email, String senha) {
		return clienteDAO.recuperaPorLoginESenha(email, senha);
	}
	
	public List<Cliente> todos(){
		return clienteDAO.todos();
	}
	
	public void editCliente(Cliente cliente){
		clienteDAO.update(cliente);
	}
}
