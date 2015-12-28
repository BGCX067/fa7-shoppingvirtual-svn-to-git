/**
 * 
 */
package br.com.anuncia.negocio.persistencia;

import java.util.List;

import br.com.anuncia.negocio.modelo.AreaProfissional;
import br.com.anuncia.negocio.modelo.Evento;
import br.com.anuncia.negocio.modelo.Secao;

public class EventoDAO extends GenericoDAO<Evento> {

	@SuppressWarnings("unchecked")
	public List<Evento> recuperarPorCodigoOuNome(Evento evento) {
		String clausula_where = null;

		if (evento.getId() != null)
			clausula_where = "codigo like '" + evento.getId() + "%'";

		if ((evento.getNome() != null) && (evento.getNome().length() > 0))
			clausula_where = add_or(clausula_where) + "nome like '%"
					+ evento.getNome() + "%'";

		if ((evento.getStatus() != null) && (evento.getStatus().length() > 0)
				&& (!evento.getStatus().equals("t")))
			clausula_where = add_and(clausula_where) + "status = '"
					+ evento.getStatus() + "'";

		return findAll(clausula_where);
	}

	public List<Evento> pesquisarEventos(String pesquisar) {
		return getEntityManager().createQuery(
//				"from Evento where " + "nome like '%" + pesquisar
//						+ "%' or descricao like '%" + pesquisar + "%'")
				"from Evento")
				.getResultList();
	}

	public List<Evento> recuperarEventos(Evento evento) {
		String clausula_where = null;

		clausula_where = "codigo like '" + evento.getId() + "%'"
				+ "nome like '%" + evento.getNome() + "%'"
				+ "pagamentoStatus like '" + evento.getPagamentoStatus() + "%'";

		return findAll(clausula_where);
	}

	public List<Evento> pesquisarEventos(String pesquisa, Long idTipoEvento,
			Long idSecao, Long idAreaProfissional) {
		return getEntityManager().createQuery(
				"from Evento e where " + "e.nome like '%" + pesquisa
						+ "%' or e.descricao like '%" + pesquisa
						+ "%' or e.tipoEvento.id=" + idTipoEvento
						+ " or e.areaProfissional.id=" + idAreaProfissional
						+ " or e.secao.id=" + idSecao).getResultList();
	}
	
	public boolean exiteEventosComAreaProfissional(AreaProfissional area) {
		
	    return existe(" o.id_area_profissional = "+ area.getId().toString());
	    	
	}
	
	public boolean existeEventoComSecao(Secao sec) {
		// TODO Auto-generated method stub
		return existe(" o.id_secao = "+ sec.getId().toString());
		
	}

}