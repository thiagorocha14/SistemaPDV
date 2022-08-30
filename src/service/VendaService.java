package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import modelo.Produto;
import modelo.Venda;

@Stateless
public class VendaService extends GenericService<Venda> {

	public VendaService() {
		super(Venda.class);
	}
	
	public List<Venda> listarVendasRelatorio(Date data1, Date data2, String tipoPag) {
		List<Venda> vendas = new ArrayList<Venda>();
		final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();	
    	final CriteriaQuery<Venda> cq = cb.createQuery(Venda.class);
    	final Root<Venda> rVenda= cq.from(Venda.class);
		
		cq.select(rVenda);
//		cq.where(cb.equals(rVenda.<String>get("pagamento"), tipoPag));
    	vendas = getEntityManager().createQuery(cq).getResultList();
		return vendas;
	}
	
	public List<Venda> listarVendasRelatorio(Date data1, Date data2) {
		List<Venda> vendas = new ArrayList<Venda>();
		final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();	
    	final CriteriaQuery<Venda> cq = cb.createQuery(Venda.class);
    	final Root<Venda> rVenda = cq.from(Venda.class);
		
		cq.select(rVenda);
//		cq.where(cb.like(rVenda.<String>get("nome"), "%".concat(rVenda).concat("%")));
    	vendas = getEntityManager().createQuery(cq).getResultList();
		return vendas;
	}
}

