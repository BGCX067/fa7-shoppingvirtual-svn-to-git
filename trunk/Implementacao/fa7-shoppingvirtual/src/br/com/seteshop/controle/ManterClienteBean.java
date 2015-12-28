/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.seteshop.controle;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;

import br.com.seteshop.negocio.ClienteFachada;
import br.com.seteshop.negocio.modelo.Cliente;
import br.com.seteshop.negocio.modelo.Usuario;


public class ManterClienteBean {

	// Objetos utilizados pela p�gina
	private Long id;
	private Cliente cliente;
	private String confirmarSenha;
	private String mensagemDeErro;
	
	// Objetos para acesso ao neg�cio
	private ClienteFachada clienteFachada;
	
	public ManterClienteBean() {
		// TODO Auto-generated constructor stub
		clienteFachada = new ClienteFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (cliente == null) {
			cliente = new Cliente();
		}
		
		if (confirmarSenha == null) {
			confirmarSenha = new String();
		}
		
		if (clienteFachada == null) {
			clienteFachada = new ClienteFachada();
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
	 * @return Returns the usuario.
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param usuario The usuario to set.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	private void limpar_cliente() {
		cliente = new Cliente();
	}
	
	public String incluir() {
		setMensagemDeErro("");
		limpar_cliente();
		return "incluir_cliente";
	}
	
	private void pegaParametroId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}

	public String salvar_novo() {
		setMensagemDeErro("");
		
		if (!getConfirmarSenha().equals(cliente.getSenha())) {
			setMensagemDeErro("Confirmacao de Senha invalida!");
			return null;
		}
		
		clienteFachada.salvar(cliente);
		return "logoff";
	}
	
	public String editar() {
		setMensagemDeErro("");
		return "editar_cliente";
	}
	
	public String salvar() {
		setMensagemDeErro("");
		
		if (!getConfirmarSenha().equals(cliente.getSenha())) {
			setMensagemDeErro("Confirmacao de Senha invalida!");
			return null;
		}
		
		clienteFachada.salvar(cliente);
		//limpar_usuario();
		return "logoff";
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getMensagemDeErro() {
		return mensagemDeErro;
	}

	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}
	
	//Listagem de clientes
	private ArrayDataModel clientes;
	
	public ArrayDataModel getClientes() {
		return clientes;
	}

	public void setClientes(ArrayDataModel clientes) {
		this.clientes = clientes;
	}

	public ArrayDataModel getTodosClientes() {
			setClientes(new ArrayDataModel(clienteFachada.todos().toArray()));
			return clientes;
    }
	
	//Editar Cliente
	
	public Cliente getClienteFromEditOrDelete(){
		Cliente cliente = (Cliente)clientes.getRowData();
		return cliente;
	}

	public String editarCliente(){
		Cliente cliente = getClienteFromEditOrDelete();
		setCliente(cliente);
		return "editarcliente";
	}

	public String atualizarCliente() {
	    clienteFachada.editCliente(cliente);
		return "atualizado";
	}

}