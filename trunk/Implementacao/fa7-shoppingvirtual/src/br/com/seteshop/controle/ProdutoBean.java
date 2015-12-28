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
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import br.com.seteshop.exceptions.ProdutoException;
import br.com.seteshop.negocio.CarrinhoFachada;
import br.com.seteshop.negocio.DepartamentoFachada;
import br.com.seteshop.negocio.LojaFachada;
import br.com.seteshop.negocio.ProdutoFachada;
import br.com.seteshop.negocio.modelo.Departamento;
import br.com.seteshop.negocio.modelo.Item;
import br.com.seteshop.negocio.modelo.Loja;
import br.com.seteshop.negocio.modelo.Produto;

public class ProdutoBean {
	
	private ProdutoFachada produtoFachada;
	
	private DepartamentoFachada departamentoFachada;
	
	private LojaFachada lojaFachada;
	
	private List<Produto> listaProdutos;
	
	private List<Departamento> departamentos;
	
	/**
	 * lista de Produtos utilizada na pesquisa de Produtos.
	 */
	private List<Produto> produtos;
	
	private Produto produto;	
	
	private String nome;
	
	private Long id;
	
	private Long idDepartamento;
	
	private String mensagemDeInformacao;
	
	private String mensagemDeErro;
	
	private List<Item> itens;
	
	private boolean chamadaPesquisar = false;
	
	/**
	 * Quantidade de produtos a ser add no carrinho
	 */
	private Long quantidade = 1L;
	
	// Logger
	private static final Logger log = Logger
			.getLogger(ProdutoBean.class);
	
	/**
	 * 
	 */
	private List<PesquisaProdutoBean> pesquisaProdutoBeans;
	
	/**
	 * lista de Lojas utilizada na pesquisa de Produtos
	 */
	private List<Loja> lojas;
	
	private UploadedFile arquivo;
	

	@PostConstruct
	public void init() {
	
		if(departamentoFachada == null){
			departamentoFachada = new DepartamentoFachada();
		}
		if(produtoFachada == null){
			produtoFachada = new ProdutoFachada();
		}
	
		if( produtos == null ){
	
			produtos = new ArrayList<Produto>();			
		}
		
		if( departamentos == null ){
			
			departamentos = new ArrayList<Departamento>();
			
			getRecuperaDepartamentos();			
		}
		
		if(produto == null){
			produto = new Produto();
		}		
		
		setMensagemDeInformacao("");
		
		//produtos = produtoFachada.findAll();
	}
	
	public String pesquisarProdutoporNome() {
		try {
			produtos = new ArrayList<Produto>();
			lojas = new ArrayList<Loja>();
			pesquisaProdutoBeans = new ArrayList<PesquisaProdutoBean>();
			produtoFachada = new ProdutoFachada();
			
			produtos = produtoFachada.recuperaPorCodigoOuNome(produto);
			//produtos = produtoFachada.recuperaPorNome(produto);
			
			//Percorre todos os produtos com o nome pesquisado
			for (Produto prod : produtos) {
				lojaFachada = new LojaFachada();
				lojas = lojaFachada.findPorProduto(prod.getNome());
				//Verifica se alguma loja possui aquele produto pesquisado
				if (lojas != null && !lojas.isEmpty()) {
					//Adiciona a Loja e o Produto na lista
					for (Loja l : lojas) {
						PesquisaProdutoBean pesquisa = new PesquisaProdutoBean();
						pesquisa.setLoja(l);
						pesquisa.setProduto(prod);
						pesquisaProdutoBeans.add(pesquisa);
					}
					
				}
			}
		} catch (Exception e) {
			log.error("Pesquisar produto por nome", e);
			return "sucesso";
		}
		return "sucesso";
	}
	
	public String adicionarProdutoCarrinho() {
		try {
			CarrinhoFachada carrinhoFachada = new CarrinhoFachada();
			//carrinhoFachada.adicionarItemCarrinho(produto);
			Item item = new Item();
			item.setProduto(produto);
			item.setQuantidade(quantidade);
			itens = new ArrayList<Item>();
			itens.add(item);
		} catch (Exception e) {
			log.error("Adicionar produto carrinho", e);
		}
		return "detalha_carrinho";
	}
	
	
	/*
	 * Construtor Padrao
	 */
	public ProdutoBean(){
		produtoFachada = new ProdutoFachada();
		departamentoFachada = new DepartamentoFachada();
	}
	
	private void limpar_produto(){
		this.produto = new Produto();
		produtos = null;
	}
	
	private void pegaParametroId(){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> resquestParams = context.getExternalContext().getRequestParameterMap();
		setId(Long.valueOf(resquestParams.get("id").toString()));
	}
	
//	responsavel por recuperar todos os departamentos
	public List<SelectItem> getRecuperaDepartamentos(){

		departamentos = departamentoFachada.findAll();
		List<SelectItem> listaDepartamento = new ArrayList<SelectItem>();
		for(Departamento depat : departamentos){
			listaDepartamento.add(new SelectItem(depat, depat.getNome()));
		}
		return listaDepartamento;
	}
	
	public String salvar() {

		try {
			FacesContext context = FacesContext.getCurrentInstance();

			// pega informaÃ§Ãµes do contexto para
			// conseguir o caminho fÃ­sico
			// necessÃ¡rio para fazer o upload de arquivo
			ServletContext sc = (ServletContext) context.getExternalContext()
					.getContext();

			// verifica se há um arquivo para salvar
			if (arquivo != null) {
				// o tipo do arquivo enviado
				String tipoDeArquivo = arquivo.getContentType();

				// verifica se um tipo de arquivo permitido
				if (tipoDeArquivo.equals("image/jpeg")
						|| tipoDeArquivo.equals("image/pjpeg")
						|| tipoDeArquivo.equals("image/gif")) {

					InputStream stream = arquivo.getInputStream();

					int fileSize = (int) arquivo.getSize();
					byte[] buffer = new byte[(int) fileSize];
					String nomeArquivo = this.separaNomeImagem(arquivo
							.getName());

					// chama o método que salva o arquivo
					Boolean salvarImagem = this.salvarArquivo(buffer, stream,
							nomeArquivo, fileSize, sc.getRealPath("/pictures"));

					if (!salvarImagem) {
						System.out.println("Problema no upload do arquivo");
					}

					produto.setImagem(nomeArquivo);
				}
			}
			
			if(produto.getId() == null){
//				produto.getDepartamento().setId(idDepartamento);
				produtoFachada.salvar(produto);				
			}
			setMensagemDeInformacao(produtoFachada.getMensagemInformacao());
		} catch (IOException e) {
			log.error("Salvar", e);
		}
		limpar_produto();
		return "voltar";
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
			log.error("salvar arquivo", e);
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
		limpar_produto();
		return "editar";
	}
	
	public String editar(){
		pegaParametroId();
		
		if((getId() != null) && (getId().longValue() > 0))
			produto = produtoFachada.recuperarProduto(getId());
//			idDepartamento = produto.getDepartamento().getId();
		
		return "editar";
	}
	
	public String excluir() {
			
			pegaParametroId();
			
			if ((getId() != null) && (getId().longValue() > 0))
				produto = produtoFachada.recuperarProduto(getId());
			
			produtoFachada.excluir(produto);
			limpar_produto();
			setMensagemDeInformacao("Produto excluído com sucesso!");
			return null;
	}
	
	public String pesquisar(){
		setChamadaPesquisar(true);
		if(getMensagemDeInformacao() != null || !getMensagemDeInformacao().equals("")){
			setMensagemDeInformacao(null);
		}
		try {
			produtos = produtoFachada.recuperaPorCodigoOuNome(produto);
			if(produtos == null || produtos.isEmpty()){
				setMensagemDeInformacao("Nenhum Produto Encontrado");
			}
		} catch (ProdutoException e) {			
			setMensagemDeInformacao("Nenhum Produto Encontrado");
			log.error("PESQUISAR", e);			
		}
		return null;
	}
	
	
    /**
     * 
     * Gets & Sets
     */
	public LojaFachada getLojaFachada() {
		return lojaFachada;
	}

	public void setLojaFachada(LojaFachada lojaFachada) {
		this.lojaFachada = lojaFachada;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<PesquisaProdutoBean> getPesquisaProdutoBeans() {
		return pesquisaProdutoBeans;
	}

	public void setPesquisaProdutoBeans(
			List<PesquisaProdutoBean> pesquisaProdutoBeans) {
		this.pesquisaProdutoBeans = pesquisaProdutoBeans;
	}

	public List<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}

	public ProdutoFachada getProdutoFachada() {
		return produtoFachada;
	}
	
	public void setProdutoFachada(ProdutoFachada produtoFachada) {
		this.produtoFachada = produtoFachada;
	}
	
	public boolean isChamadaPesquisar() {
		return chamadaPesquisar;
	}

	public void setChamadaPesquisar(boolean chamadaPesquisar) {
		this.chamadaPesquisar = chamadaPesquisar;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

	public String getMensagemDeErro() {
		return mensagemDeErro;
	}

	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Produto getNovo() {
		produto = new Produto();
		return produto;
	}

	public DepartamentoFachada getDepartamentoFachada() {
		return departamentoFachada;
	}

	public void setDepartamentoFachada(DepartamentoFachada departamentoFachada) {
		this.departamentoFachada = departamentoFachada;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public List<Item> getItens() {
		return itens;
	}
	

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public String getMensagemDeInformacao() {
		return mensagemDeInformacao;
	}

	public void setMensagemDeInformacao(String mensagemDeInformacao) {
		this.mensagemDeInformacao = mensagemDeInformacao;
	}
	
	
	
}
