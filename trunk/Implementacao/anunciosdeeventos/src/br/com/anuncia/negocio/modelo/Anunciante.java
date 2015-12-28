package br.com.anuncia.negocio.modelo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Anunciante {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 100)
	private String nome;
	@Column(nullable = false, length = 100)
	private String rua;
	@Column(nullable = false, length=6)
	private String numero;
	@Column(nullable = false, length = 100)
	private String complemento;
	@Column(nullable = false, length = 60)
	private String bairro;
	@Column(nullable = false, length = 15)
	private String telefone;
	@Column(nullable = false, length = 1)
	private String sexo;
	@Column(nullable = false, length = 11)
	private String cpf;
	@Column(nullable = false, length = 80)
	private String email;
	@Column(nullable = false)
	private Date dataNascimento;
	@Column(nullable = false, length = 20)
	private String senha;
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Evento> eventos;
	@Column(nullable = true)
	private Date dataStatus;
	@Column(nullable = true, length = 1)
	private String status;
	@Column(nullable = true, length = 255)
	private String motivoStatus;

	
	public Date getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(Date dataStatus) {
		this.dataStatus = dataStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMotivoStatus() {
		return motivoStatus;
	}
	public void setMotivoStatus(String motivoStatus) {
		this.motivoStatus = motivoStatus;
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
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Set<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}
}
