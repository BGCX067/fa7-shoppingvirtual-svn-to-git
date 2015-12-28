package br.com.seteshop.controle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import br.com.seteshop.exceptions.ItemException;
import br.com.seteshop.exceptions.ProdutoException;
import br.com.seteshop.negocio.LojaFachada;
import br.com.seteshop.negocio.ItemFachada;
import br.com.seteshop.negocio.modelo.Carrinho;
import br.com.seteshop.negocio.modelo.Item;
import br.com.seteshop.negocio.modelo.Loja;
import br.com.seteshop.negocio.modelo.Item;

public class ManterItemBean {
	
	private ItemFachada itemFachada;
	
	private List<Item> listaitens;
	
	private Item item;
	
	private Long id;
	
	private String mensagemDeErro;
	
	/**
	 * 
	 */
	private List<ManterItemBean> manterItemBeans;
	
	private UploadedFile arquivo;

	public ManterItemBean(){}

	@PostConstruct
	public void init() {
	
		if(itemFachada == null){
			itemFachada = new ItemFachada();
		}
	
		if( listaitens == null ){
	
			listaitens = new ArrayList<Item>();
			
			listarItens();
	
		}
		
		if(item == null){
			item = new Item();
		}
		
	}
	
	public String pesquisarItemporCod() {
		try {
			listaitens = new ArrayList<Item>();
			manterItemBeans = new ArrayList<ManterItemBean>();
			itemFachada = new ItemFachada();
			
			listaitens = itemFachada.recuperaPorCodigoOuNome(item);
			
		} catch (Exception e) {
			// TODO: handle exception
			return "erro";
		}
		return "sucesso";
		
	}
	
	private void listarItens(){
		listaitens = itemFachada.listarItens();
	}
	
	private void limpar_item(){
		this.item = new Item();
	}
	
	private void pegaParametroId(){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}
	
	public String salvar() {
		try {
//			departamentoFachada.validarDepartamento(departamento);
			itemFachada.salvar(item);
			limpar_item();
			return "voltar";
		} catch (Exception e) {
			mensagemDeErro = e.getMessage();
			return null;
		}
	}
	
	public boolean salvarArquivo(byte[] buf, InputStream stream,
			String nomeArquivo, int fileSize, String path) throws IOException {

		nomeArquivo = this.separaNomeImagem(nomeArquivo);

		File file = new File(path + "/" + nomeArquivo);

		FileOutputStream output = new FileOutputStream(file);

		try {
			while (true) {
				int count = stream.read(buf, 0, fileSize);
				if (count == -1)
					break;
				output.write(buf, 0, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			output.flush();
			output.close();
			stream.close();
		}

		return true;
	}
	
	
	private String separaNomeImagem(String nomeImagem) {

		if (nomeImagem.lastIndexOf("\\") >= -1) {
			nomeImagem = nomeImagem.substring(nomeImagem.lastIndexOf("\\") + 1);
		} else if (nomeImagem.lastIndexOf("/") >= -1) {
			nomeImagem = nomeImagem.substring(nomeImagem.lastIndexOf("/") + 1);
		}

		return nomeImagem;
	}
	
	
	public String incluir(){
		limpar_item();
		return "editar";
	}
	
	public String editar(){
		pegaParametroId();
		
		if((getId() != null) && (getId().longValue() > 0)){
			item = itemFachada.recuperarItem(getId());
		}
		return "editar";
	}
	
	public String excluir() {
			
			pegaParametroId();
			
			if ((getId() != null) && (getId().longValue() > 0))
				item = itemFachada.recuperarItem(getId());
			
			itemFachada.excluir(item);
			limpar_item();
			return null;
	}
	
	public String pesquisar() throws ItemException{
		try {
			listaitens = itemFachada.recuperaPorCodigoOuNome(item);
		} catch (ItemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
    /**
     * 
     * Gets & Sets
     */


	public List<Item> getItens() {
		return listaitens;
	}

	public void setItens(List<Item> itens) {
		this.listaitens = itens;
	}

	public List<ManterItemBean> getManterItemBeans() {
		return manterItemBeans;
	}

	public void setPesquisaProdutoBeans(
			List<ManterItemBean> pesquisaProdutoBeans) {
		this.manterItemBeans = manterItemBeans;
	}

	public ItemFachada getProdutoFachada() {
		return itemFachada;
	}
	
	public void setItemFachada(ItemFachada itemFachada) {
		this.itemFachada = itemFachada;
	}
	
	public List<Item> getListaItens() {
		return listaitens;
	}

	public void setListaitens(List<Item> listaitens) {
		this.listaitens = listaitens;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensagemDeErro() {
		return mensagemDeErro;
	}

	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}
	
	public Item getNovo() {
		item = new Item();
		return item;
	}
	
}
