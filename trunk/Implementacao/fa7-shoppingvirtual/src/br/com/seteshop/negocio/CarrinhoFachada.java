package br.com.seteshop.negocio;

import java.util.ArrayList;
import java.util.List;

import br.com.seteshop.exceptions.FormasDePagamentoException;
import br.com.seteshop.exceptions.ManterCarrinhoException;
import br.com.seteshop.negocio.modelo.Carrinho;
import br.com.seteshop.negocio.modelo.FormasDePagamento;
import br.com.seteshop.negocio.modelo.Item;
import br.com.seteshop.negocio.modelo.Produto;
import br.com.seteshop.negocio.persistencia.CarrinhoDAO;
import br.com.seteshop.negocio.persistencia.FormasDePagamentoDAO;

public class CarrinhoFachada {

	private FormasDePagamentoDAO FormasDePagamentoDAO;

	private CarrinhoDAO carrinhoDAO;
	
	public CarrinhoFachada() {
		FormasDePagamentoDAO = new FormasDePagamentoDAO();
		carrinhoDAO = new CarrinhoDAO();
	}

	public List<FormasDePagamento> recuperaPorNome(
			FormasDePagamento FormasDePagamento)
			throws FormasDePagamentoException {
		if (FormasDePagamento != null) {
			List<FormasDePagamento> FormasDePagamentos = FormasDePagamentoDAO.recuperarPorCodigoOuNome(FormasDePagamento);
			if (FormasDePagamentos == null || FormasDePagamentos.isEmpty()) {
				throw new FormasDePagamentoException(
						"Nenhuma Forma De Pagamento foi encontrado");
			}
			return FormasDePagamentos;
		}
		throw new FormasDePagamentoException("Preencha o campo pesquisa");
	}
	
	public List<Item> recuperarItensCarrinho(Carrinho carrinho) throws ManterCarrinhoException {
		List<Carrinho> list = new ArrayList<Carrinho>();
		
		if (carrinho != null) {
			list =  carrinhoDAO.recuperarItensCarrinho(carrinho);
			if (list != null && !list.isEmpty()) {
				return list.get(0).getItens();
			} else {
				throw new ManterCarrinhoException("Lista Vazia");
			}
		} else {
			throw new ManterCarrinhoException("Nenhum carrinho existente");
		}
		
	}
	
	public void adicionarItemCarrinho(Produto produto) {
		Carrinho carrinho = new Carrinho();
		
		Item item = new Item();
		item.setProduto(produto);
		
		List<Item> itens = new ArrayList<Item>();
		itens.add(item);
		
		carrinho.setItens(itens);
		
		carrinhoDAO.save(carrinho);
	}
}
