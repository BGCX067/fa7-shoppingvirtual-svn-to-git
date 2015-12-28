/**
 * 
 */
package br.com.seteshop.negocio.persistencia;

import java.util.List;

import br.com.seteshop.negocio.modelo.FormasDePagamento;
import br.com.seteshop.negocio.modelo.Secao;

public class FormasDePagamentoDAO extends GenericoDAO<FormasDePagamento> {

	public List<FormasDePagamento> recuperarPorCodigoOuNome(
			FormasDePagamento FormasDePagamento) {
		String clausula_where = null;

		if (FormasDePagamento.getId() != null)
			clausula_where = "id like '" + FormasDePagamento.getId() + "%'";

		if ((FormasDePagamento.getNome() != null)
				&& (FormasDePagamento.getNome().length() > 0))
			clausula_where = add_or(clausula_where) + "nome like '%"
					+ FormasDePagamento.getNome() + "%'";

		return findAll(clausula_where);
	}
	@Override
	public List<FormasDePagamento> findAll(){
		return getEntityManager().createQuery(
				"from FormasDePagamento order by nome DESC").getResultList();
	}
}