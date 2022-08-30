package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Produto;
import service.ProdutoService;

@ViewScoped
@ManagedBean
public class ProdutoBean {
	
	@EJB
	private ProdutoService produtoService;
	
	private Produto produto = new Produto();
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@PostConstruct
	protected void iniciar() {
		listarProdutos();
	}
	
	public void listarProdutos() {
		produtos = produtoService.listAll();
	}
	
	public void gravar() {
		String msg="";
		if(produto.getId()==null) {
			produtoService.create(produto);
			msg = "gravado";
		}else {
			produtoService.merge(produto);
			msg = "atualizado";
		}
		
		produto = new Produto();
		listarProdutos();
		FacesContext.getCurrentInstance().addMessage
		("msg1", new FacesMessage("Produto "+msg+" com Sucesso!"));
	}
	
	
	public void carregarProduto(Produto a) {
		produto = a;
	}
	
	public void excluirProduto(Produto a) {
		produtoService.remove(a);
		listarProdutos();
		FacesContext.getCurrentInstance().addMessage
		("msg1", new FacesMessage("Produto excluído com Sucesso!"));
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
