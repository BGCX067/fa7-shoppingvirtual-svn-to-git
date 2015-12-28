package br.com.seteshop.controle;

import java.util.List;

import br.com.seteshop.negocio.CarrinhoFachada;
import br.com.seteshop.negocio.DepartamentoFachada;
import br.com.seteshop.negocio.LojaFachada;
import br.com.seteshop.negocio.modelo.Cliente;
import br.com.seteshop.negocio.modelo.Item;
import br.com.seteshop.negocio.modelo.Loja;
import br.com.seteshop.negocio.modelo.Produto;

public class ManterCarrinhoBean {
	//pagina
	private Long id;
	private Cliente cliente;
	private List<Item> itens;
	
	private CarrinhoFachada carrinhoFachada;
	
	private String mensagemDeErro;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public ManterCarrinhoBean() {
		// TODO Auto-generated constructor stub
		carrinhoFachada = new CarrinhoFachada();
	}
	public String getMensagemDeErro() {
		return mensagemDeErro;
	}
	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}	
	
	public String pesquisar() {
		
		return null;
	}
	
	public String salvar() {
		
			return null;
	}
	
	public String excluir(){
		
		return null;
		
	}
}
