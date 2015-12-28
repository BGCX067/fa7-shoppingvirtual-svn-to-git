/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.seteshop.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.seteshop.negocio.DepartamentoFachada;
import br.com.seteshop.negocio.modelo.Departamento;
import br.com.seteshop.negocio.modelo.Secao;

public class ManterDepartamentoBean {

	// Objetos utilizados pela pagina
	private Long id;
	private Departamento departamento;
	private List<Departamento> departamentos;
	
	// Objetos para acesso ao negocio
	private DepartamentoFachada departamentoFachada;
	
	private String mensagemDeErro;
	
	/**
	 * @return Returns the mensagemDeErro.
	 */
	public String getMensagemDeErro() {
		return mensagemDeErro;
	}

	/**
	 * @param mensagemDeErro The mensagemDeErro to set.
	 */
	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}

	public ManterDepartamentoBean() {
		// TODO Auto-generated constructor stub
		departamentoFachada = new DepartamentoFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (departamento == null) {
			departamento = new Departamento();
		}
		
		if (departamentoFachada == null) {
			departamentoFachada = new DepartamentoFachada();
		}
		
		if( departamentos == null ){
			departamentos = new ArrayList<Departamento>();
		}
		
		departamentos = departamentoFachada.findAll();
	}
	
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
	 * @return Returns the departamento.
	 */
	public Departamento getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento The departamento to set.
	 */
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	/**
	 * @return Returns the departamentos.
	 */
	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	/**
	 * @param departamento
	 */
	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}
	
	private void limpar_departamento() {
		departamento = new Departamento();
	}
	
	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}
	
	public String pesquisar() {
		departamentos = departamentoFachada.recuperarDepartamentosPorCodigoOuNome(departamento);	
		return null;
	}
	
	public String incluir() {
		limpar_departamento();	
		return "editar";
	}
		
	public String editar() {
		
		pegaParametroId();
		
		if ((getId() != null) && (getId().longValue() > 0))
			departamento = departamentoFachada.recuperarDepartamento(getId());
		
		return "editar";
	}
	
	public String salvar() {
		try {
			departamentoFachada.validarDepartamento(departamento);
			departamentoFachada.salvar(departamento);
			limpar_departamento();
			departamentos = departamentoFachada.recuperarDepartamentosPorCodigoOuNome(departamento);	
			mensagemDeErro="";
			return "voltar";
		} catch (Exception e) {
			mensagemDeErro = e.getMessage();
			return null;
		}
	}
	
	public String excluir() {
		
    	pegaParametroId();
    	
    	Departamento departamento_temp = departamento;
    	
		if ((getId() != null) && (getId().longValue() > 0))
			departamento = departamentoFachada.recuperarDepartamento(getId());
		departamentoFachada.excluir(departamento);
		limpar_departamento();
/*
		if (!anuncianteFachada.exiteEventosComDepartamento(departamento)) 
		{
			administradorFachada.excluir(departamento);
			departamento = area_profissional_temp;
			pesquisar();
		} else {
			mensagemDeErro = "�rea Profissional n�o pode ser exclu�da, por que existe(m) evento(s) associado(s)";
		}
		*/
		departamentos = departamentoFachada.recuperarDepartamentosPorCodigoOuNome(departamento);	
		return null;
	}
}
