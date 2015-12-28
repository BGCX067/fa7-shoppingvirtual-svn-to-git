/**
 * 
 */
package br.com.seteshop.negocio.persistencia;

import java.util.List;

import br.com.seteshop.negocio.modelo.Departamento;
import br.com.seteshop.negocio.modelo.Secao;

public class SecaoDAO extends GenericoDAO<Secao> {

	@SuppressWarnings("unchecked")
	public List<Secao> recuperarPorCodigoOuNome(
			Secao secao) {
		String clausula_where = null;

		if (secao.getId() != null)
			clausula_where = "id like '%" + secao.getId() + "%'";

		if ((secao.getNome() != null)
				&& (secao.getNome().length() > 0))
			clausula_where = add_or(clausula_where) + "nome like '%"
					+ secao.getNome() + "%'";

		return findAll(clausula_where);
	}
	
	public List<Secao> recuperarPorNome(Secao secao) {
		String clausula_where = null;

		if ((secao.getNome() != null) && (secao.getNome().length() > 0))
			clausula_where = "nome = '" + secao.getNome() + "'";

		return findAll(clausula_where);
	}
	
	@Override
	public List<Secao> findAll(){
		return getEntityManager().createQuery(
				"from Secao order by nome DESC").getResultList();
	}
}