package br.com.seteshop.negocio.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 100)
	private String nome;
	@Column(nullable = false, length = 80)
	private String email;
	@Column(nullable = false, length = 20)
	private String senha;
	
	@Column(nullable = false, length = 100)
	private String sobrenome;
	@Column(nullable = true, length = 9)
	private String cep;
	@Column(nullable = true, length = 50)
	private String cidade;
	@Column(nullable = true, length = 2)
	private String uf;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	@Column(nullable = true, length = 11)
	private String cpf;
	@Column(nullable = true, length = 12)
	private String telRes;
	@Column(nullable = true, length = 12)
	private String telCom;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="carrinho")
	private Carrinho carrinho;
	
	
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
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelRes() {
		return telRes;
	}
	public void setTelRes(String telRes) {
		this.telRes = telRes;
	}
	public String getTelCom() {
		return telCom;
	}
	public void setTelCom(String telCom) {
		this.telCom = telCom;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	
	
}
