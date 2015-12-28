package br.com.seteshop.negocio;

import java.util.List;

import br.com.seteshop.exceptions.ProdutoException;
import br.com.seteshop.negocio.modelo.Produto;
import br.com.seteshop.negocio.persistencia.ProdutoDAO;

public class ProdutoFachada {

	private ProdutoDAO produtoDAO;
	
	private String mensagemInformacao;
	
	public ProdutoFachada(){
		produtoDAO = new ProdutoDAO();
	}
	
	public List<Produto> recuperaPorCodigoOuNome(Produto produto) throws ProdutoException {
		if (produto != null){
			List<Produto> produtos = produtoDAO.recuperarPorCodigoOuNome(produto);
			if (produtos == null || produtos.isEmpty()){
				throw new ProdutoException("Nenhum Produto foi encontrado");
			}
			return produtos;
		}
		throw new ProdutoException("Preencha o campo pesquisa");
	}
	
	public List<Produto> findAll(){
		return produtoDAO.findAll();
	}
	
	public List<Produto> recuperarProdutosPorCodigoOuNome(
			Produto produto) {
		return produtoDAO.recuperarPorCodigoOuNome(produto);
	}
	
	public Produto recuperarProduto(Long id){
		return produtoDAO.recuperarProdutoPorId(id);
	}
	
	public void excluir(Produto produto){
		 produtoDAO.remove(produto);
	}
	
	public void salvar(Produto produto){
		if(produto.getId() != null){
			produtoDAO.update(produto);
			setMensagemInformacao("Operação realizada com sucesso!");
		}else {
			produtoDAO.save(produto);
			setMensagemInformacao("Operação realizada com sucesso!");
		}
	}

	public List<Produto> recuperarProdutosPorCodigoLoja(Long idLoja) throws ProdutoException{
        if (idLoja != null){
            List<Produto> produtos = produtoDAO.recuperarPorCodigoLoja(idLoja);
            
            if (produtos == null || produtos.isEmpty()){
                throw new ProdutoException("Nenhum Produto foi encontrado");
            }
            return produtos;
        }
        throw new ProdutoException("Loja sem Produtos");
    }
    
    public List<Produto> recuperarProdutosPorCodigoDepartamento(Long idDepartamento) throws ProdutoException{
        if (idDepartamento != null){
            List<Produto> produtos = produtoDAO.recuperarPorCodigoDepartamento(idDepartamento);
            
            if (produtos == null || produtos.isEmpty()){
                throw new ProdutoException("Nenhum Produto foi encontrado");
            }
            return produtos;
        }
        throw new ProdutoException("Loja sem Produtos");
    }

	public String getMensagemInformacao() {
		return mensagemInformacao;
	}

	public void setMensagemInformacao(String mensagemInformacao) {
		this.mensagemInformacao = mensagemInformacao;
	}
	
}
