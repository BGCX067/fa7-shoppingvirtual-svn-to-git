package br.com.seteshop.negocio.modelo;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Loja {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 14)
	private String cpfCnpj;
	@Column(nullable = true, length = 140)
	private String razaoSocial;
	@Column(nullable = true, length = 140)
	private String nomeFantasia;
	@Embedded
	@AttributeOverrides({@AttributeOverride(name="tipoEndereco", column=@Column(nullable = true, length = 10)),
		@AttributeOverride(name="endereco", column=@Column(nullable = true, length = 250)),
		@AttributeOverride(name="numero", column=@Column(nullable = true, length = 10)),
		@AttributeOverride(name="complemento", column=@Column(nullable = true, length = 140)),
		@AttributeOverride(name="bairro", column=@Column(nullable = true, length = 140)),
		@AttributeOverride(name="cidade", column=@Column(nullable = true, length = 50)),
		@AttributeOverride(name="uf", column=@Column(nullable = true, length = 2))})
	private Endereco endereco;
	@Column(nullable = true, length = 12)
	private String telefone;
	@Column(nullable = true, length = 100)
	private String logo;

	@ManyToOne
	@JoinColumn(name = "secao")
	private Secao secao;

	@OneToMany
	private List<Produto> produtos;

	@ManyToOne
	@JoinColumn(name = "lojista")
	private Lojista lojista;

	@ManyToMany(mappedBy = "lojas")
	private List<Departamento> departamentos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Lojista getLojista() {
		return lojista;
	}

	public void setLojista(Lojista lojista) {
		this.lojista = lojista;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}