package br.com.anuncia.negocio.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Long id;
	@Column(nullable = false, length = 80)
	private String nome;
	@Column(nullable = false, length = 255)
	private String descricao;
	@Column(nullable = true, length = 100)
	private String local;
	@Column(nullable = true, length = 100)
	private String endereco;
	@Column(nullable = true)
	private Date dataInicio;
	@Column(nullable = true)
	private Date dataFim;
	@Column(nullable = true, length = 5)
	private String horaInicio;
	@Column(nullable = true, length = 5)
	private String horaFim;
	@Column(nullable = true)
	private Date dataVisualizacaoInicio;
	@Column(nullable = true)
	private Date dataVisualizacaoFim;
	@Column(nullable = true)
	private Date dataCadastro;
	@Column(nullable = true)
	private BigDecimal valor;
	@Column(nullable = true)
	private Date dataStatus;
	@Column(nullable = true, length = 1)
	private String status;
	@Column(nullable = true, length = 255)
	private String motivoStatus;
	@Column(nullable = true, length = 1)
	private String pagamentoStatus;
	@Column(nullable = true)
	private Date dataPagamentoStatus;
	//@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "evento")
	//private Set<Avaliacao> avaliacoes;
	@Column(nullable=true)
	private Integer totalDeVisualizacao;
	@OneToMany(cascade=CascadeType.ALL)
	private List<MensagemEvento> mensagens;
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "id_secao", nullable=false)
	private Secao secao;
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "id_tipo_evento", nullable=false)
	private TipoEvento tipoEvento;
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "id_area_profissional", nullable=false)
	private AreaProfissional areaProfissional;

	public Integer getTotalDeVisualizacao() {
		if(totalDeVisualizacao==null)
			totalDeVisualizacao = Integer.valueOf(0);
		return totalDeVisualizacao;
	}

	public void setTotalDeVisualizacao(Integer totalDeVisualizacao) {
		this.totalDeVisualizacao = totalDeVisualizacao;
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
	public Date getDataVisualizacaoInicio() {
		return dataVisualizacaoInicio;
	}

	public void setDataVisualizacaoInicio(Date dataVisualizacaoInicio) {
		this.dataVisualizacaoInicio = dataVisualizacaoInicio;
	}

	public Date getDataVisualizacaoFim() {
		return dataVisualizacaoFim;
	}

	public void setDataVisualizacaoFim(Date dataVisualizacaoFim) {
		this.dataVisualizacaoFim = dataVisualizacaoFim;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Date dataStatus) {
		this.dataStatus = dataStatus;
	}
	public Date getDataPagamentoStatus() {
		return dataPagamentoStatus;
	}

	public void setDataPagamentoStatus(Date dataPagamentoStatus) {
		this.dataPagamentoStatus = dataPagamentoStatus;
	}

	/**
	 * @return Returns the valor.
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor The valor to set.
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return Returns the horaInicio.
	 */
	public String getHoraInicio() {
		return horaInicio;
	}

	/**
	 * @param horaInicio The horaInicio to set.
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * @return Returns the horaFim.
	 */
	public String getHoraFim() {
		return horaFim;
	}

	/**
	 * @param horaFim The horaFim to set.
	 */
	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return Returns the motivoStatus.
	 */
	public String getMotivoStatus() {
		return motivoStatus;
	}

	/**
	 * @param motivoStatus The motivoStatus to set.
	 */
	public void setMotivoStatus(String motivoStatus) {
		this.motivoStatus = motivoStatus;
	}

	/**
	 * @return Returns the pagamentoStatus.
	 */
	public String getPagamentoStatus() {
		
		if ((pagamentoStatus == null) || (pagamentoStatus.equals(""))) {
			return "Pagamento pendente, aguardando validação do Administrador do Sistema";
		} else {
			return pagamentoStatus;
		}
	}

	/**
	 * @param pagamentoStatus The pagamentoStatus to set.
	 */
	public void setPagamentoStatus(String pagamentoStatus) {
		this.pagamentoStatus = pagamentoStatus;
	}

	/**
	 * @return Returns the mensagens.
	 */
	public List<MensagemEvento> getMensagens() {
		return mensagens;
	}

	/**
	 * @param mensagens The mensagens to set.
	 */
	public void setMensagens(List<MensagemEvento> mensagens) {
		this.mensagens = mensagens;
	}

	/**
	 * @return Returns the secao.
	 */
	public Secao getSecao() {
		return secao;
	}

	/**
	 * @param secao The secao to set.
	 */
	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	/**
	 * @return Returns the tipoEvento.
	 */
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	/**
	 * @param tipoEvento The tipoEvento to set.
	 */
	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	/**
	 * @return Returns the areaProfissional.
	 */
	public AreaProfissional getAreaProfissional() {
		return areaProfissional;
	}

	/**
	 * @param areaProfissional The areaProfissional to set.
	 */
	public void setAreaProfissional(AreaProfissional areaProfissional) {
		this.areaProfissional = areaProfissional;
	}
}