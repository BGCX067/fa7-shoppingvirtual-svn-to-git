package br.com.seteshop.negocio;

import java.util.List;

import br.com.seteshop.exceptions.FormasDePagamentoException;
import br.com.seteshop.negocio.modelo.FormasDePagamento;
import br.com.seteshop.negocio.persistencia.FormasDePagamentoDAO;
import br.com.seteshop.exceptions.ExcecaoNegocio;

public class FormasDePagamentoFachada {

	private FormasDePagamentoDAO FormasDePagamentoDAO;

	public FormasDePagamentoFachada() {
		FormasDePagamentoDAO = new FormasDePagamentoDAO();
	}

	public List<FormasDePagamento> recuperaPorNome(FormasDePagamento FormasDePagamento)
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
	
	public List<FormasDePagamento> findAll() {
		return FormasDePagamentoDAO.findAll();
	}
	
	public List<FormasDePagamento> recuperarPorCodigoOuNome(
			FormasDePagamento formasDePagamento) {
		return FormasDePagamentoDAO.recuperarPorCodigoOuNome(formasDePagamento);
	}

	public FormasDePagamento recuperarFormasDePagamento(Long id) {
		return FormasDePagamentoDAO.findById(id);
	}

	public void validarFormasDePagamento(FormasDePagamento formasDePagamento)
			throws ExcecaoNegocio {

		if (formasDePagamento.getId() == null) {
			List<FormasDePagamento> lista = (List<FormasDePagamento>) FormasDePagamentoDAO.recuperarPorCodigoOuNome(formasDePagamento);
			if (lista != null && lista.size() > 0)
				throw new ExcecaoNegocio(
						"Nome da Forma de Pagamento deve ser unico");
		}
	}

	public void salvar(FormasDePagamento formasDePagamento) {
		FormasDePagamentoDAO.save(formasDePagamento);
	}

	public void excluir(FormasDePagamento formasDePagamento) {
		FormasDePagamentoDAO.remove(formasDePagamento);

	}
	
	
}
