package br.com.seteshop.negocio;

import java.util.List;

import br.com.seteshop.exceptions.ExcecaoNegocio;
import br.com.seteshop.exceptions.ProdutoException;
import br.com.seteshop.negocio.modelo.Secao;
import br.com.seteshop.negocio.persistencia.SecaoDAO;

public class SecaoFachada {
		
	private SecaoDAO secaoDAO;
	
	public SecaoFachada() {
		secaoDAO = new SecaoDAO();
	}
	
	public List<Secao> recuperaPorNome(Secao secao) throws ProdutoException {
		if (secao != null){
			List<Secao> secoes = secaoDAO.recuperarPorNome(secao);
			if (secoes == null || secoes.isEmpty()){
				throw new ProdutoException("Nenhum Secao foi encontrada");
			}
			return secoes;
		}
		throw new ProdutoException("Preencha o campo pesquisa");
	}
	
	public List<Secao> findAll() {
		return secaoDAO.findAll();
	}
	
	public List<Secao> recuperarSecoesPorCodigoOuNome(
			Secao secao) {
		return secaoDAO.recuperarPorCodigoOuNome(secao);
	}

	public Secao recuperarSecao(Long id) {
		return secaoDAO.findById(id);
	}

	public void validarSecao(Secao secao)
			throws ExcecaoNegocio {

		if (secao.getId() == null) {
			List<Secao> lista = (List<Secao>) secaoDAO.recuperarPorNome(secao);
			if (lista != null && lista.size() > 0)
				throw new ExcecaoNegocio(
						"Nome da secao deve ser unico");
		}
	}

	public void salvar(Secao secao) {
		secaoDAO.save(secao);
	}

	public void excluir(Secao secao) {
		secaoDAO.remove(secao);

	}
	
}