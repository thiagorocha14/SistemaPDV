package modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Venda {

	@Id @GeneratedValue
	private Long id;
	
	private String cpfCliente;
	private Date dataVenda;
	
	@OneToMany(mappedBy="venda")
	private List<ItemVenda> itens;
	
	@OneToOne
	private Pagamento pagamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public Double getTotalVenda() {
		Double totalVenda = (double) 0;
		for (ItemVenda item : this.itens) {
			totalVenda = totalVenda + item.getTotalItem();
		}
		return totalVenda;
	}
	
	public void adicionarItem(ItemVenda I) {
		this.itens.add(I);
	}
	
}
