package br.com.seteshop.negocio.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, length = 100)
	private String descricao;
	
	@Column(nullable = false)
	private double preco;
	
	private String imagem;
	
	@Column(nullable = false)
	private int quantidadeEstoque;
	
	@ManyToOne
	@JoinColumn(name = "departamento")
	private Departamento departamento;
	
	@ManyToOne
	@JoinColumn(name="loja")
	private Loja loja;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy="produto")
	private List<Item> item;	
	
	
	/**
	 * Add construtor padrao
	 * @author: Igor Melao (igormelao@gmail.com)
	 * @Since: 26/10/2010
	 */
	public Produto (){}
	
	
	/*
	 * Construtor Sobrecarregado
	 */
	public Produto(Long id) {
		super();
		this.id = id;
	}	
	
	
	public Produto(Long id,String nome, String descricao, double preco, String imagem,
			int quantidadeEstoque, Departamento departamento,Loja loja, List<Item> item) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.imagem = imagem;
		this.quantidadeEstoque = quantidadeEstoque;
		this.departamento = departamento;
		this.item = item;
	}



	/**
	 * gets && sets
	 */
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}	
	public Loja getLoja() {
		return loja;
	}	public void setLoja(Loja loja) {
		this.loja = loja;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}

	
	/**
	 * Métodos Equals and HashCode
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
