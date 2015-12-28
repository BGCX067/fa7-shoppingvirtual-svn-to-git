/**
 * 
 */
package br.com.anuncia.negocio.persistencia;

import java.util.List;

import br.com.anuncia.negocio.modelo.AreaProfissional;
import br.com.anuncia.negocio.modelo.TipoEvento;

public class TipoEventoDAO extends GenericoDAO<TipoEvento> {

	@SuppressWarnings("unchecked")
	public List<TipoEvento> recuperarPorCodigoOuNome(TipoEvento tipoEvento) {
		
		String clausula_where = null;

		if (tipoEvento.getId() != null)
			clausula_where = "id_tipo_evento like '%" + tipoEvento.getId() + "%'";

		if ((tipoEvento.getNome() != null)
				&& (tipoEvento.getNome().length() > 0))
			clausula_where = add_or(clausula_where) + "nome like '%"
					+ tipoEvento.getNome() + "%'";

		return findAll(clausula_where);
	}
	
	public List<TipoEvento> recuperarPorNomeExato(TipoEvento tipoEvento) {
		String clausula_where = null;

		if ((tipoEvento.getNome() != null) && (tipoEvento.getNome().length() > 0))
			clausula_where = "nome = '" + tipoEvento.getNome() + "'";

		return findAll(clausula_where);
	}
}
