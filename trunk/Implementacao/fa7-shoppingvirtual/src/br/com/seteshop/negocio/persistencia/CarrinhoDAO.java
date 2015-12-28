/**
 * 
 */
package br.com.seteshop.negocio.persistencia;

import java.sql.ClientInfoStatus;
import java.util.List;

import br.com.seteshop.negocio.modelo.Carrinho;
import br.com.seteshop.negocio.modelo.Cliente;
import br.com.seteshop.negocio.modelo.Cliente;
import br.com.seteshop.negocio.modelo.Item;

public class CarrinhoDAO extends GenericoDAO<Carrinho> {

	public List<Cliente> recuperarPorCliente(
			Cliente cliente) {
		String clausula_where = null;

		if ((cliente.getNome() != null)
				&& (cliente.getNome().length() > 0))
			clausula_where = add_or(clausula_where) + "nome like '%"
					+ cliente.getNome() + "%'";

		//return findAll(clausula_where);
		return null;
	}
	
	public List<Carrinho> recuperarItensCarrinho(Carrinho carrinho) {
		String clausula_where = "id = " + carrinho.getId();
		return findAll(clausula_where);
	}

}