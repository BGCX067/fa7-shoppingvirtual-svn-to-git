package br.com.anuncia.negocio.modelo;

import java.util.Date;

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
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Long id;
	@Column(nullable = false, length = 80)
	private String nome;
	@Column(nullable = true, length = 80)
	private String rua;
	@Column(nullable = true, length = 6)
	private Integer numero;
	@Column(nullable = true, length = 30)
	private String complemento;
	@Column(nullable = true, length = 50)
	private String bairro;
	@Column(nullable = true, length = 17)
	private String telefone;
	@Column(nullable = true, length = 1)
	private String sexo;
	@Column(nullable = true, length = 11)
	private Integer cpf;
	@Column(nullable = false, length = 120)
	private String email;
	@Column(nullable = true)
	private Date dataNascimento;
	/**
	 * @return Returns the codigo.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param codigo The codigo to set.
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
	 * @return Returns the rua.
	 */
	public String getRua() {
		return rua;
	}
	/**
	 * @param rua The rua to set.
	 */
	public void setRua(String rua) {
		this.rua = rua;
	}
	/**
	 * @return Returns the numero.
	 */
	public Integer getNumero() {
		return numero;
	}
	/**
	 * @param numero The numero to set.
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	/**
	 * @return Returns the complemento.
	 */
	public String getComplemento() {
		return complemento;
	}
	/**
	 * @param complemento The complemento to set.
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	/**
	 * @return Returns the bairro.
	 */
	public String getBairro() {
		return bairro;
	}
	/**
	 * @param bairro The bairro to set.
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	/**
	 * @return Returns the telefone.
	 */
	public String getTelefone() {
		return telefone;
	}
	/**
	 * @param telefone The telefone to set.
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	/**
	 * @return Returns the sexo.
	 */
	public String getSexo() {
		return sexo;
	}
	/**
	 * @param sexo The sexo to set.
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/**
	 * @return Returns the cpf.
	 */
	public Integer getCpf() {
		return cpf;
	}
	/**
	 * @param cpf The cpf to set.
	 */
	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Returns the dataNascimento.
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}
	/**
	 * @param dataNascimento The dataNascimento to set.
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
