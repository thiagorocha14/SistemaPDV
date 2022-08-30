package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.ItemVenda;
import modelo.Produto;
import modelo.Venda;
import service.ItemVendaService;
import service.ProdutoService;
import service.VendaService;

@ViewScoped
@ManagedBean
public class VendaBean {

	@EJB
	private VendaService vendaService;
	
	@EJB
	private ItemVendaService itemVendaService;
	
	@EJB
	private ProdutoService produtoService;
	
	private Venda venda = new Venda();
	private ItemVenda item = new ItemVenda();
	
	private List<Produto> produtos = new ArrayList<Produto>();
	private Long idProdutoAtual = 0L;
	
	@PostConstruct
	protected void iniciar() {
		listarProdutos();
	}
	
	public void listarProdutos() {
		produtos = produtoService.listAll();
	}
	
	public void adicionarItem() {
		Produto produto = produtoService.obtemPorId(idProdutoAtual);
		item.setProduto(produto);
		venda.adicionarItem(item);
		item = new ItemVenda();
		idProdutoAtual = 0L;
	}
	
	public void gravar() {
		
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public ItemVenda getItem() {
		return item;
	}

	public void setItem(ItemVenda item) {
		this.item = item;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Long getIdProdutoAtual() {
		return idProdutoAtual;
	}

	public void setIdProdutoAtual(Long idProdutoAtual) {
		this.idProdutoAtual = idProdutoAtual;
	}
	
	
	
	

}
