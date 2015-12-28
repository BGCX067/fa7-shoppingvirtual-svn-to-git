package br.com.anuncia.negocio.persistencia;

import java.util.List;

import br.com.anuncia.negocio.modelo.MensagemEvento;

public class MensagemEventoDAO extends GenericoDAO<MensagemEvento> {

	public List<MensagemEvento> pesquisarMensagemEventos(Long pesquisar) {
		return getEntityManager().createQuery(
				"from MensagemEvento where " + "evento_codigo like '%"
						+ pesquisar + "%'").getResultList();
	}

	public List<MensagemEvento> recuperarPorCodigoOuMensagem(
			MensagemEvento mensagemEvento) {
		String clausula_where = null;

		if (mensagemEvento.getId() != null)
			clausula_where = "codigo like '%" + mensagemEvento.getId() + "%'";

		if ((mensagemEvento.getMensagem() != null)
				&& (mensagemEvento.getMensagem().length() > 0))
			clausula_where = add_or(clausula_where) + "mensagem like '%"
					+ mensagemEvento.getMensagem() + "%'";

		return findAll(clausula_where);
	}

	public List<MensagemEvento> recuperarPorEventoEStatus(Long idEvento,
			String status) {
		String clausula_where = null;
		if (idEvento != null) {
			clausula_where = "m.evento.id=" + idEvento;
		}
		if ((status != null) && (status.length() > 0) && (!status.equals("t"))) {
			clausula_where = add_and(clausula_where) + "m.status='" + status
					+ "'";
		}
		String statment = "from MensagemEvento m";
		if (clausula_where != null)
			statment += " where " + clausula_where;
		return getEntityManager().createQuery(statment).getResultList();
	}

}
