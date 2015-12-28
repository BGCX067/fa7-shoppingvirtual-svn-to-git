/**
 * 
 */
package br.com.anuncia.negocio.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.anuncia.negocio.modelo.Avaliacao;
import br.com.anuncia.negocio.modelo.Evento;
import br.com.anuncia.negocio.modelo.Usuario;

public class AvaliacaoDAO extends GenericoDAO<Avaliacao> {

	public List<Avaliacao> listarAvaliacoesPorID(Long id) {
		return getEntityManager().createQuery(
				"from Avaliacao where usuario_id = " + id).getResultList();
	}

	public Avaliacao pegaAvaliacaoPorId(Long idEvento) {
		String id = idEvento.toString();
		return (Avaliacao) getEntityManager().createQuery(
				"from Avaliacao where id = '" + id + "' ").getSingleResult();
	}

	public Avaliacao recuperarPorUsuarioEvento(Usuario usuario, Evento evento) {
		try {
			return (Avaliacao) getEntityManager().createQuery(
					"from Avaliacao where usuario_id = '" + usuario.getId()
							+ "' and evento_codigo = '" + evento.getId() + "'")
					.getSingleResult();
		} catch (NoResultException re) {
			return null;
		}
	}

}
