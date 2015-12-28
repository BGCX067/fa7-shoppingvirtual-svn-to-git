/**
 * 
 */
package br.com.anuncia.negocio.persistencia;

import java.util.List;

import br.com.anuncia.negocio.modelo.AreaProfissional;
import br.com.anuncia.negocio.modelo.Evento;

public class AreaProfissionalDAO extends GenericoDAO<AreaProfissional> {

	@SuppressWarnings("unchecked")
	public List<AreaProfissional> recuperarPorCodigoOuNome(
			AreaProfissional areaProfissional) {
		String clausula_where = null;

		if (areaProfissional.getId() != null)
			clausula_where = "codigo like '%" + areaProfissional.getId() + "%'";

		if ((areaProfissional.getNome() != null)
				&& (areaProfissional.getNome().length() > 0))
			clausula_where = add_or(clausula_where) + "nome like '%"
					+ areaProfissional.getNome() + "%'";

		return findAll(clausula_where);
	}
	
	public List<AreaProfissional> recuperarPorNomeExato(AreaProfissional areaProfissional) {
		String clausula_where = null;

		if ((areaProfissional.getNome() != null) && (areaProfissional.getNome().length() > 0))
			clausula_where = "nome = '" + areaProfissional.getNome() + "'";

		return findAll(clausula_where);
	}

}