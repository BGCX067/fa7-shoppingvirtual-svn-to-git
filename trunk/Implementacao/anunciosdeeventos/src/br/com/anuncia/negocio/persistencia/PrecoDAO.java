/*
 * Created on 08/12/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.negocio.persistencia;

import java.util.List;

import br.com.anuncia.negocio.modelo.Preco;

public class PrecoDAO extends GenericoDAO<Preco> {

	public List<Preco> recuperarPorCodigoOuMensagem(Preco preco) {
		String clausula_where = null;

		if (preco.getId() != null)
			clausula_where = "codigo like '%" + preco.getId() + "%'";

		if ((preco.getSecao() != null))
			clausula_where = add_or(clausula_where) + "mensagem like '%"
					+ preco.getSecao() + "%'";

		return findAll(clausula_where);
	}

	public List<Preco> recuperarPorSecao(Long id) {
		String statment = "from Preco p where p.secao.id=" + id;

		return getEntityManager().createQuery(statment).getResultList();
	}

	

}
