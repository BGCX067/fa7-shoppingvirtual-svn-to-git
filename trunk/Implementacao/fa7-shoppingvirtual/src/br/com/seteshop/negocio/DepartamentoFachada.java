package br.com.seteshop.negocio;

import java.util.List;

import br.com.seteshop.exceptions.ExcecaoNegocio;
import br.com.seteshop.exceptions.ProdutoException;
import br.com.seteshop.negocio.modelo.Departamento;
import br.com.seteshop.negocio.persistencia.DepartamentoDAO;

public class DepartamentoFachada {
		
	private DepartamentoDAO departamentoDAO;
	
	public DepartamentoFachada() {
		departamentoDAO = new DepartamentoDAO();
	}
	
	public List<Departamento> recuperaPorNome(Departamento departamento) throws ProdutoException {
		if (departamento != null){
			List<Departamento> departamentos = departamentoDAO.recuperarPorNome(departamento);
			if (departamentos == null || departamentos.isEmpty()){
				throw new ProdutoException("Nenhum Produto foi encontrado");
			}
			return departamentos;
		}
		throw new ProdutoException("Preencha o campo pesquisa");
	}
	
	public List<Departamento> findAll() {
		return departamentoDAO.findAll();
	}
	
	public List<Departamento> recuperarDepartamentosPorCodigoOuNome(
			Departamento departamento) {
		return departamentoDAO.recuperarPorCodigoOuNome(departamento);
	}

	public Departamento recuperarDepartamento(Long id) {
		return departamentoDAO.findById(id);
	}

	public void validarDepartamento(Departamento departamento)
			throws ExcecaoNegocio {

		if (departamento.getId() == null) {
			List<Departamento> lista = (List<Departamento>) departamentoDAO.recuperarPorNome(departamento);
			if (lista != null && lista.size() > 0)
				throw new ExcecaoNegocio(
						"Nome do departamento deve ser único");
		}
	}

	public void salvar(Departamento departamento) {
		departamentoDAO.save(departamento);
	}

	public void excluir(Departamento departamento) {
		departamentoDAO.remove(departamento);

	}
	
}