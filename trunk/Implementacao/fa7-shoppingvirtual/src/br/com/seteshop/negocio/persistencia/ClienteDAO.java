/**
 * 
 */
package br.com.seteshop.negocio.persistencia;

import java.util.List;

import br.com.seteshop.negocio.modelo.Cliente;
import br.com.seteshop.negocio.modelo.Usuario;

public class ClienteDAO extends GenericoDAO<Cliente> {

	public ClienteDAO() {
		// TODO Auto-generated constructor stub

	}

	@SuppressWarnings("unchecked")
	
	public  List<Cliente> todos(){
		return findAll();
	}
	public List<Cliente> recuperarPorCodigoOuNome(Cliente cliente) {
		String clausula_where = null;

		if (cliente.getId() != null)
			clausula_where = "id like '" + cliente.getId() + "%'";

		if ((cliente.getNome() != null) && (cliente.getNome().length() > 0))
			clausula_where = add_or(clausula_where) + "nome like '"
					+ cliente.getNome() + "%'";

		return findAll(clausula_where);
	}
	
	public Cliente recuperaPorLoginESenha(String login, String senha) {
		String clausula_where = "email = '" + login + "' and senha = '" + senha + "' ";
	  return (Cliente) getEntityManager().createQuery(
				"from " + getPersistentClass().getName() + " where "
				+ clausula_where).getSingleResult();
	}

	public Cliente recuperaPorLogin(String login) {
		String clausula_where = "email = '" + login +"'";
		  return (Cliente) getEntityManager().createQuery(
					"from " + getPersistentClass().getName() + " where "
					+ clausula_where).getSingleResult();
	}

}