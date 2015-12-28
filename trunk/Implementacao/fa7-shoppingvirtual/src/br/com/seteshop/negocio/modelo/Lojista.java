package br.com.seteshop.negocio.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Lojista {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 30)
	private String nome;
	@Column(nullable = false, length = 30)
	private String sobrenome;
	@Column(nullable = false, length = 30)
	private String email;
	@Column(nullable = false, length = 30)
	private String senha;
	
	@Column(nullable = true, length = 30)
	private String cep;
	@Column(nullable = true, length = 30)
	private String cidade;
	@Column(nullable = true, length = 2)	
	private String uf;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@Column(nullable = true, length = 30)
	private String cnpj;
	@Column(nullable = true, length = 30)
	private String  telRes;
	@Column(nullable = true, length = 30)
	private String telCom;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "lojista")
	private List<Loja> lojas;
	
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
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
	public List<Loja> getLojas() {
		return lojas;
	}
	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	
}
