package br.com.seteshop.negocio;

import java.util.List;

import br.com.seteshop.exceptions.ItemException;
import br.com.seteshop.negocio.modelo.Item;
import br.com.seteshop.negocio.persistencia.ItemDAO;

public class ItemFachada {

	private ItemDAO itemDAO;
	
	public ItemFachada(){
		itemDAO = new ItemDAO();
	}
	
	public List<Item> recuperaPorCodigoOuNome(Item item) throws ItemException {
		if (item != null){
			List<Item> itens = itemDAO.recuperaPorCodigo(item);
			if (itens == null || itens.isEmpty()){
				throw new ItemException("Nenhum Item foi encontrado");
			}
			return itens;
		}
		throw new ItemException("Preencha o campo pesquisa");
	}
	
	public List<Item> listarItens(){
		return itemDAO.findAll();
	}
	
	public Item recuperarItem(Long id){
		return itemDAO.recuperarItemPorId(id);
	}
	
	public void excluir(Item item){
		 itemDAO.remove(item);
	}
	
	public void salvar(Item item){
		if(item.getId() != null){
			itemDAO.update(item);
		}else {
			itemDAO.save(item);
		}
	}
	
}
