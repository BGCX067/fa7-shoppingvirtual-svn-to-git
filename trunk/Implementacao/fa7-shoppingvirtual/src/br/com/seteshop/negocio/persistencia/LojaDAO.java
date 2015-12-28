/**
 * 
 */
package br.com.seteshop.negocio.persistencia;

import java.util.ArrayList;
import java.util.List;

import br.com.seteshop.negocio.modelo.Loja;

public class LojaDAO extends GenericoDAO<Loja> {

	@SuppressWarnings("unchecked")
	public List<Loja> recuperarPorCodigoOuNome(Loja loja) {
		String clausula_where = null;

		if (loja.getId() != null)
			clausula_where = "id like '%" + loja.getId() + "%'";

		if ((loja.getNomeFantasia() != null)
				&& (loja.getNomeFantasia().length() > 0))
			clausula_where = add_or(clausula_where) + "nomeFantasia like '%"
					+ loja.getNomeFantasia() + "%'";

		return findAll(clausula_where);
	}

	public List<Loja> recuperarPorNome(Loja loja) {
		String clausula_where = null;

		if ((loja.getNomeFantasia() != null)
				&& (loja.getNomeFantasia().length() > 0))
			clausula_where = "nomeFantasia = '" + loja.getNomeFantasia() + "'";

		return findAll(clausula_where);
	}

	public List<Loja> recuperarPorProduto(String nomeProduto) {
		if (nomeProduto != null && !nomeProduto.equals("")) {

			List<Loja> retorno = new ArrayList<Loja>();

			List<Loja> list = findQueryNative("SELECT l.nomeFantasia FROM LOJA l,DEPARTAMENTO d, "
					+ "DEPLOJA dl, PRODUTO p WHERE p.departamento = d.id AND d.id = dl.id_depto AND "
					+ "l.id = dl.id_loja AND p.nome LIKE '%"
					+ nomeProduto
					+ "%';");
			Object[] ob = list.toArray();
			for (Object obj : ob) {
				Loja loja = new Loja();
				loja.setNomeFantasia(obj.toString());

				retorno.add(recuperarPorNome(loja).get(0));
			}

			return retorno;
			/*
			 * return findQueryNative("SELECT l.* FROM LOJA l,DEPARTAMENTO d, "
			 * +
			 * "DEPLOJA dl, PRODUTO p WHERE p.departamento = d.id AND d.id = dl.id_depto AND "
			 * + "l.id = dl.id_loja AND p.nome LIKE '%" + nomeProduto + "%';");
			 */
		}
		return null;
	}

}