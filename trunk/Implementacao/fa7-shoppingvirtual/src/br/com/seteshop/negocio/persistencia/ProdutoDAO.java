package br.com.seteshop.negocio.persistencia;

import java.util.List;

import br.com.seteshop.negocio.modelo.Produto;

public class ProdutoDAO extends GenericoDAO<Produto> {

	@SuppressWarnings("unchecked")
	public List<Produto> recuperarPorCodigoOuNome(Produto produto){
		String clausula_where = null;
		
		if (produto.getId() != null){
			clausula_where = add_or(clausula_where) + "id like '" + produto.getId() + "%'";
		}
		
		if(produto.getNome() != null && !produto.getNome().equals("")){
			clausula_where = add_or(clausula_where) + "nome like '%" + produto.getNome() + "%'";
			//return findAll("nome like '" + produto.getNome() + "'");
		}
		return findAll(clausula_where);
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> recuperarPorCodigoLoja(Long id){
		String clausula_where = null;
		
		if (id != null){
			clausula_where = add_or(clausula_where) + "loja = " + id;
		}
		
		return findAll(clausula_where);
	}
	
	@SuppressWarnings("unchecked")
    public List<Produto> recuperarPorCodigoDepartamento(Long id){
        String clausula_where = null;
        
        if (id != null){
            clausula_where = add_or(clausula_where) + "departamento = " + id;
        }
        
        return findAll(clausula_where);
    }
	
	public Produto recuperarProdutoPorId(Long idProduto){
		String id = idProduto.toString();
		return (Produto) getEntityManager().createQuery(
				"from Produto where id = '" + id + "' ").getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> findAll(){
		
		List<Produto> produtos = getEntityManager().createQuery(
		"from Produto order by id DESC").getResultList();
		
		return produtos;
	}
	
}
