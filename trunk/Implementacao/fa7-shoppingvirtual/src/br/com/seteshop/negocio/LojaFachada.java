package br.com.seteshop.negocio;

import java.util.List;

import br.com.seteshop.exceptions.ExcecaoNegocio;
import br.com.seteshop.exceptions.ProdutoException;
import br.com.seteshop.negocio.modelo.Loja;
import br.com.seteshop.negocio.persistencia.LojaDAO;

public class LojaFachada {

	private LojaDAO lojaDAO;

	public LojaFachada() {
		lojaDAO = new LojaDAO();
	}

	public List<Loja> recuperaPorNome(Loja loja) throws ProdutoException {
		if (loja != null) {
			List<Loja> lojas = lojaDAO.recuperarPorNome(loja);
			if (lojas == null || lojas.isEmpty()) {
				throw new ProdutoException("Nenhum Produto foi encontrado");
			}
			return lojas;
		}
		throw new ProdutoException("Preencha o campo pesquisa");
	}

	public List<Loja> findAll() {
		return lojaDAO.findAll();
	}

	public List<Loja> findPorProduto(String nomeProduto) {
		return lojaDAO.recuperarPorProduto(nomeProduto);
	}

	public List<Loja> recuperarLojasPorCodigoOuNome(Loja loja) {
		return lojaDAO.recuperarPorCodigoOuNome(loja);
	}

	public Loja recuperarLoja(Long id) {
		return lojaDAO.findById(id);
	}

	public void validarLoja(Loja loja) throws ExcecaoNegocio {

		if (loja.getId() == null) {
			List<Loja> lista = (List<Loja>) lojaDAO.recuperarPorNome(loja);
			if (lista != null && lista.size() > 0)
				throw new ExcecaoNegocio(
						"Nome da �rea Profissional deve ser �nico");
		}
	}

	public void salvar(Loja loja) {
		lojaDAO.save(loja);
	}

	public void excluir(Loja loja) {
		lojaDAO.remove(loja);

	}

}
