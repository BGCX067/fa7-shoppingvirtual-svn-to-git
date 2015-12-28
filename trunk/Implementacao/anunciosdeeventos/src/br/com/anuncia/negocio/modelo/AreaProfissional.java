package br.com.anuncia.negocio.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AreaProfissional {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_area_profissional")
	private Long id;
	@Column(nullable = false, length = 80)
	private String nome;
	@Column(nullable = false, length = 255)
	private String descricao;
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//private Set<AreaProfissional> subArea;

	public AreaProfissional(Long id) {
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	public AreaProfissional() {
		// TODO Auto-generated constructor stub
	}

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
/*
	public Set<AreaProfissional> getSubArea() {
		return subArea;
	}

	public void setSubArea(Set<AreaProfissional> subArea) {
		this.subArea = subArea;
	}
*/
}
