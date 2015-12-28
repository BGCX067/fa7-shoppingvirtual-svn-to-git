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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="departamento")
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "departamento")
	private List<Produto> produtos;
	
	@ManyToMany
	@JoinTable(name="DepLoja", 
			joinColumns = {@JoinColumn(name="id_depto")}, 
			inverseJoinColumns={@JoinColumn(name="id_loja")})
	private List<Loja> lojas;
	
	
	/**
	 * Add construtor padrao
	 * @Autor: Igor Melão (igormelao@gmail.com)
	 * @Since: 26/10/2010
	 */
	public Departamento(){}
	
	/*
	 * Construtor Sobrecarregado
	 */
	public Departamento(Long id) {
		super();
		this.id = id;
	}
	
	public Departamento(Long id, String nome, String descricao,
			List<Produto> produtos, List<Loja> lojas) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.produtos = produtos;
		this.lojas = lojas;
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
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public List<Loja> getLojas() {
		return lojas;
	}
	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
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
		Departamento other = (Departamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
