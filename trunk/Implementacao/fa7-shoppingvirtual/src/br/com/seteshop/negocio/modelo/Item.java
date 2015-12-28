package br.com.seteshop.negocio.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private Long quantidade;
	@Column(nullable = false)
	private Double desconto;
	@ManyToOne
	@JoinColumn(name="carrinho")
	private Carrinho carrinho;
	@ManyToOne
	@JoinColumn(name="produto")
	private Produto produto;
	
	/**
	 * Add construtor padrao
	 * @author Igor Melão
	 * @since 26/10/2010
	 * */
	public Item(){}
	
	
	/**
	 * Construtor sobrecarregado
	 */
	
	public Item(Long id) {
		super();
		this.id = id;
	}
	
	public Item(Long id, Long quantidade, Double desconto, Carrinho carrinho,
			Produto produto) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.desconto = desconto;
		this.carrinho = carrinho;
		this.produto = produto;
	}

	/**
	 * Métodos Get's and Set's
	 */
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
	/**
	 * Métodos HashCode and Equals
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
