/**
 * 
 */
package br.com.anuncia.negocio.persistencia;

import java.util.List;

import br.com.anuncia.negocio.modelo.Evento;
import br.com.anuncia.negocio.modelo.Secao;

public class SecaoDAO extends GenericoDAO<Secao> {

	@SuppressWarnings("unchecked")
	public List<Secao> recuperarPorCodigoOuNome(Secao secao) {
		String clausula_where = null;

		if (secao.getId() != null)
			clausula_where = "id_secao like '" + secao.getId() + "%'";

		if ((secao.getNome() != null) && (secao.getNome().length() > 0))
			clausula_where = add_or(clausula_where) + "nome like '"
					+ secao.getNome() + "%'";

		return findAll(clausula_where);
	}
	//PESQUISAR
	public List<Secao> pesquisarSecoes(String pesquisar) {
		return getEntityManager().createQuery(
				"from Evento where " + "nome like '%" + pesquisar
						+ "%' or descricao like '%" + pesquisar + "%'")
				.getResultList();
	}
	
	public Integer pesquisarTotalEventosPorSecao(Long id) {
		return (Integer) getEntityManager().createQuery(
				"select count(*) as Total from Evento ev where ev.secao.id="+id)
				.getSingleResult();
	}

}