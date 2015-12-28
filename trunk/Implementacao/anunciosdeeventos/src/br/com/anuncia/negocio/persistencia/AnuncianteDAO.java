/**
 * 
 */
package br.com.anuncia.negocio.persistencia;

import java.util.List;

import br.com.anuncia.negocio.modelo.Anunciante;


public class AnuncianteDAO extends GenericoDAO<Anunciante> {
	
	
		
	@SuppressWarnings("unchecked")
	public List<Anunciante> recuperarPorCodigoOuNome(Anunciante anunciante) {
		String clausula_where = null;

		if (anunciante.getId() != null)
			clausula_where = "id like '" + anunciante.getId() + "%'";

		if ((anunciante.getNome() != null) && (anunciante.getNome().length() > 0))
			clausula_where = add_or(clausula_where) + "nome like '%"
					+ anunciante.getNome() + "%'";

		if ((anunciante.getStatus() != null) && (anunciante.getStatus().length() > 0) && (!anunciante.getStatus().equals("t")))
			clausula_where = add_and(clausula_where) + "status = '"
					+ anunciante.getStatus() + "'";
		
		return findAll(clausula_where);
	}
	
	public Anunciante recuperaPorLoginESenha(String login, String senha) {
		String clausula_where = "email = '" + login + "' and senha = '" + senha + "' ";
	  return (Anunciante) getEntityManager().createQuery(
				"from " + getPersistentClass().getName() + " where "
				+ clausula_where).getSingleResult();
	}

	}