/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.controle;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.anuncia.negocio.UsuarioFachada;
import br.com.anuncia.negocio.modelo.Cliente;


public class ManterClienteBean {

	// Objetos utilizados pela página
	private Long id;
	private Cliente cliente;
	private List<Cliente> clientes;
	
	// Objetos para acesso ao negócio
	private UsuarioFachada usuarioFachada;
	
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

	public ManterClienteBean() {
		// TODO Auto-generated constructor stub
		usuarioFachada = new UsuarioFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (cliente == null) {
			cliente = new Cliente();
		}
		
		if (usuarioFachada == null) {
			usuarioFachada = new UsuarioFachada();
		}
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
	 * @return Returns the cliente.
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente The cliente to set.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * @return Returns the clientes.
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * @param clientes The clientes to set.
	 */
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	private void limpar_cliente() {
		cliente = new Cliente();
		clientes = null;
	}
	
	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}
	
	public String pesquisar() {
		clientes = usuarioFachada.recuperarClientePorCodigoOuNome(cliente);
		if (clientes == null || clientes.size() == 0)
			mensagemDeErro = "A pesquisa não retornou nenhum cliente";
		return null;
	}
	
	public String incluir() {
		limpar_cliente();	
		return "editar";
	}
		
	public String editar() {
		
		pegaParametroId();
		
		System.out.println("Id do Cliente="+getId());
		
		if ((getId() != null) && (getId().longValue() > 0))
			cliente = usuarioFachada.recuperarCliente(getId());
		
		//limpar_cliente();
		return "editar";
	}
	
	public String salvar() {
		usuarioFachada.salvar(cliente);
		limpar_cliente();
		return "voltar";
	}
	
	public String excluir() {
		
		pegaParametroId();
		
		if ((getId() != null) && (getId().longValue() > 0))
			cliente = usuarioFachada.recuperarCliente(getId());
		
		usuarioFachada.excluir(cliente);
		limpar_cliente();
		return null;
	}
}