/**
 * 
 */
package br.com.seteshop.negocio.persistencia;

import java.util.List;

import br.com.seteshop.negocio.modelo.Departamento;

public class DepartamentoDAO extends GenericoDAO<Departamento> {

	@SuppressWarnings("unchecked")
	public List<Departamento> recuperarPorCodigoOuNome(
			Departamento departamento) {
		String clausula_where = null;

		if (departamento.getId() != null)
			clausula_where = "id like '%" + departamento.getId() + "%'";

		if ((departamento.getNome() != null)
				&& (departamento.getNome().length() > 0))
			clausula_where = add_or(clausula_where) + "nome like '%"
					+ departamento.getNome() + "%'";

		return findAll(clausula_where);
	}
	
	public List<Departamento> recuperarPorNome(Departamento departamento) {
		String clausula_where = null;

		if ((departamento.getNome() != null) && (departamento.getNome().length() > 0))
			clausula_where = "nome = '" + departamento.getNome() + "'";

		return findAll(clausula_where);
	}
}