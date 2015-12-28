package br.com.anuncia.negocio.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class TipoEvento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_tipo_evento")
	private Long id;
	@Column(nullable = false, length = 30)
	private String nome;
	@Column(nullable = false, length = 255)
	private String descricao;

	public TipoEvento(Long idTipoEvento) {
		id = idTipoEvento;
		// TODO Auto-generated constructor stub
	}
	
	public TipoEvento() {
	
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
}
