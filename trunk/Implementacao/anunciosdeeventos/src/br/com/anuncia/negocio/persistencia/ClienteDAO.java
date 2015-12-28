/**
 * 
 */
package br.com.anuncia.negocio.persistencia;

import java.util.List;

import br.com.anuncia.negocio.modelo.Cliente;

public class ClienteDAO extends GenericoDAO<Cliente> {

	public ClienteDAO() {
		// TODO Auto-generated constructor stub

	}

	@SuppressWarnings("unchecked")
	public List<Cliente> recuperarPorCodigoOuNome(Cliente cliente) {
		String clausula_where = null;

		if (cliente.getId() != null)
			clausula_where = "codigo like '" + cliente.getId() + "%'";

		if ((cliente.getNome() != null) && (cliente.getNome().length() > 0))
			clausula_where = add_or(clausula_where) + "nome like '"
					+ cliente.getNome() + "%'";

		return findAll(clausula_where);
	}

}