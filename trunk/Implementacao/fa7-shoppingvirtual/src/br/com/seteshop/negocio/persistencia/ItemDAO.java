package br.com.seteshop.negocio.persistencia;

import java.util.List;

import br.com.seteshop.negocio.modelo.Item;
import br.com.seteshop.negocio.modelo.Produto;

public class ItemDAO extends GenericoDAO<Item> {

	public List<Item> recuperaPorCodigo(Item item){
		String clausula_where = null;
		
		if (item.getId() != null){
			clausula_where = add_or(clausula_where) + "id like '" + item.getId() + "%'";
		}
		
		return findAll(clausula_where);
	}
	
	public Item recuperarItemPorId(Long idProduto){
		String id = idProduto.toString();
		return (Item) getEntityManager().createQuery(
				"from Item where id = '" + id + "' ").getSingleResult();
	}
	
	@Override
	public List<Item> findAll(){
		return getEntityManager().createQuery(
				"from Item order by id DESC").getResultList();
	}
	
}
