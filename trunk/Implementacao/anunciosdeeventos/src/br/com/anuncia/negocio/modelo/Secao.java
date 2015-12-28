package br.com.anuncia.negocio.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Régis Simão
 * 
 */
@Entity
public class Secao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_secao")
	private Long id;
	@Column(nullable = false, length = 30)
	private String nome;
	@Column(nullable = true, length = 255)
	private String descricao;
	
	public Secao(Long idSecao) {
	id = idSecao;
		// TODO Auto-generated constructor stub
	}
	public Secao() {
	// TODO Auto-generated constructor stub
}
	//	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="secao")
//	private Set<Preco> precos;
	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return Returns the nome.
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome The nome to set.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return Returns the descricao.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao The descricao to set.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
//	public Set<Preco> getPrecos() {
//		return precos;
//	}
//	public void setPrecos(Set<Preco> precos) {
//		this.precos = precos;
//	}
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}
}