package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Venda;
import service.VendaService;

@ViewScoped
@ManagedBean
public class VendaBean {

	@EJB
	private VendaService vendaService;
	
	private Venda venda = new Venda();
	private List<Venda> vendas = new ArrayList<Venda>();
	
	@PostConstruct
	protected void iniciar() {
		listarAutores();
	}
	
	public void listarAutores() {
		vendas = vendaService.listAll();
	}
	
	public void gravar() {
		String msg="";
		if(venda.getId()==null) {
			vendaService.create(venda);
			msg = "gravado";
		}else {
			vendaService.merge(venda);
			msg = "atualizado";
		}
		
		venda = new Venda();
		listarAutores();
		FacesContext.getCurrentInstance().addMessage
		("msg1", new FacesMessage("Autor "+msg+" com Sucesso!"));
	}
	
	
	public void carregarAutor(Venda a) {
		venda = a;
	}
	
	public void excluirAutor(Venda a) {
		vendaService.remove(a);
		listarAutores();
		FacesContext.getCurrentInstance().addMessage
		("msg1", new FacesMessage("Autor excluído com Sucesso!"));
	}

	public Venda getAutor() {
		return venda;
	}

	public void setAutor(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getAutores() {
		return vendas;
	}

	public void setAutores(List<Venda> autores) {
		this.vendas = autores;
	}
}
