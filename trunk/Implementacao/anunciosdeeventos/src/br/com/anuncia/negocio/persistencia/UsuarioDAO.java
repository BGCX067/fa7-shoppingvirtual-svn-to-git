/**
 * 
 */
package br.com.anuncia.negocio.persistencia;

import java.util.List;

import br.com.anuncia.negocio.modelo.Usuario;

public class UsuarioDAO extends GenericoDAO<Usuario> {

	@SuppressWarnings("unchecked")
	public List<Usuario> recuperarPorCodigoOuNome(Usuario usuario) {
		String clausula_where = null;

		if (usuario.getId() != null)
			clausula_where = "codigo like '" + usuario.getId() + "%'";

		if ((usuario.getNome() != null) && (usuario.getNome().length() > 0))
			clausula_where = add_or(clausula_where) + "nome like '"
					+ usuario.getNome() + "%'";

		return findAll(clausula_where);
	}

	public Usuario recuperaPorLoginESenha(String login, String senha) {
		String clausula_where = "email = '" + login + "' and senha = '" + senha + "' ";
	  return (Usuario) getEntityManager().createQuery(
				"from " + getPersistentClass().getName() + " where "
				+ clausula_where).getSingleResult();
	}

	public Usuario recuperaPorLogin(String login) {
		String clausula_where = "email = '" + login +"'";
		  return (Usuario) getEntityManager().createQuery(
					"from " + getPersistentClass().getName() + " where "
					+ clausula_where).getSingleResult();
	}

}