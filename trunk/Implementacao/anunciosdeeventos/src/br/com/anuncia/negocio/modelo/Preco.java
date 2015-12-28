package br.com.anuncia.negocio.modelo;

import java.math.BigDecimal;
import java.util.Date;
import javax.faces.convert.*;
import javax.faces.convert.Converter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Preco {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Column(nullable = false, precision = 9, scale = 2)
	private BigDecimal valor;
	@Column(nullable = false)
	private Date dataInicio;
	@Column(nullable = false)
	private Date dataFim;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "id_secao", nullable=false)
	private Secao secao;
	
	

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public boolean validaData(int dataInicio,int dataFim)
	{
		if(dataFim < dataInicio)
		{
			return false;
		}
		return true;
	}
	
}
