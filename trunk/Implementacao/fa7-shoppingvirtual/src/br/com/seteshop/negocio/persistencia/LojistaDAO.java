/**
 * 
 */
package br.com.seteshop.negocio.persistencia;

import java.util.List;

import br.com.seteshop.negocio.modelo.Lojista;
import br.com.seteshop.negocio.modelo.Usuario;

public class LojistaDAO extends GenericoDAO<Lojista> {

	public LojistaDAO() {
		// TODO Auto-generated constructor stub

	}

	@SuppressWarnings("unchecked")
	public List<Lojista> recuperarPorCodigoOuNome(Lojista lojista) {
		String clausula_where = null;

		if (lojista.getId() != null)
			clausula_where = "id like '" + lojista.getId() + "%'";

		if ((lojista.getNome() != null) && (lojista.getNome().length() > 0))
			clausula_where = add_or(clausula_where) + "nome like '"
					+ lojista.getNome() + "%'";

		return findAll(clausula_where);
	}
	
	public Lojista recuperaPorLoginESenha(String login, String senha) {
		String clausula_where = "email = '" + login + "' and senha = '" + senha + "' ";
	  return (Lojista) getEntityManager().createQuery(
				"from " + getPersistentClass().getName() + " where "
				+ clausula_where).getSingleResult();
	}

	public Lojista recuperaPorLogin(String login) {
		String clausula_where = "email = '" + login +"'";
		  return (Lojista) getEntityManager().createQuery(
					"from " + getPersistentClass().getName() + " where "
					+ clausula_where).getSingleResult();
	}

}