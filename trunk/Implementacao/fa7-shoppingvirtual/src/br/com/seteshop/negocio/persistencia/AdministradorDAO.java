/**
 * 
 */
package br.com.seteshop.negocio.persistencia;

import br.com.seteshop.negocio.modelo.Administrador;

public class AdministradorDAO extends GenericoDAO<Administrador> {

	public Administrador recuperaPorLoginESenha(String login, String senha) {
		String clausula_where = "usuario = '" + login + "' and senha = '" + senha + "' ";
	  return (Administrador) getEntityManager().createQuery(
				"from " + getPersistentClass().getName() + " where "
				+ clausula_where).getSingleResult();
	}

}