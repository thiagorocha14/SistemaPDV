package controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import modelo.ItemVenda;
import modelo.Produto;
import modelo.TipoPagamento;
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
	private String codigoProdutoAtual = "";
	
	private Date data1;
	private Date data2;
	
	private String tipoPagamentoFiltro;
	
	@PostConstruct
	protected void iniciar() {
		listarProdutos();
	}
	
	public void listarProdutos() {
		produtos = produtoService.listAll();
	}
	
	public void adicionarItem() {
		try{
			if (codigoProdutoAtual.isEmpty()) {
				FacesContext.getCurrentInstance().
				addMessage("msg1", new FacesMessage("É necessário informar um produto!"));
			} else {
				
					Produto produto = produtoService.obtemPorCodigo(codigoProdutoAtual);
					item.setProduto(produto);
					item.setValorUnitario(produto.getPreco());
					venda.adicionarItem(item);
					item = new ItemVenda();
					codigoProdutoAtual = "";
					
			}
		} catch (NoResultException nre){
			FacesContext.getCurrentInstance().
			addMessage("msg1", new FacesMessage("Nenhum produto encontrado com esse código!"));
		}
	}
	
	public void cancelar() {
		venda = new Venda();
		item = new ItemVenda();
		codigoProdutoAtual = "";
	}
	
	public void gravar() {
		if(venda.getItens().isEmpty()) {
			FacesContext.getCurrentInstance().
			addMessage("msg1", new FacesMessage("Não é possível finalizar uma venda sem itens!"));
		} else {
			venda.getPagamento().setValorTotal(venda.getTotalVenda());
			vendaService.create(venda);
			venda = new Venda();
			codigoProdutoAtual = "";
			FacesContext.getCurrentInstance().
			addMessage("msg1", new FacesMessage("Livro Gravado com Sucesso!"));
		}	
	}
	
	public void gerarRelatorio() {
		
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

	public String getCodigoProdutoAtual() {
		return codigoProdutoAtual;
	}

	public void setCodigoProdutoAtual(String codigoProdutoAtual) {
		this.codigoProdutoAtual = codigoProdutoAtual;
	}
	
	public TipoPagamento[] getTipoPagamento(){
		return TipoPagamento.values();
	}

	public Date getData1() {
		return data1;
	}

	public void setData1(Date data1) {
		this.data1 = data1;
	}

	public Date getData2() {
		return data2;
	}

	public void setData2(Date data2) {
		this.data2 = data2;
	}

	public String getTipoPagamentoFiltro() {
		return tipoPagamentoFiltro;
	}

	public void setTipoPagamentoFiltro(String tipoPagamentoFiltro) {
		this.tipoPagamentoFiltro = tipoPagamentoFiltro;
	}
	
	

}
