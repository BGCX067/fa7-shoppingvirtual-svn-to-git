package br.com.seteshop.controle;

import br.com.seteshop.negocio.modelo.Loja;
import br.com.seteshop.negocio.modelo.Produto;

public class PesquisaProdutoBean {

	private Produto produto;
	
	private Loja loja;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}
	
	
	
}
