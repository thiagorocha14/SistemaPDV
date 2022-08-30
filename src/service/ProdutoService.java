package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import modelo.Produto;

@Stateless
public class ProdutoService extends GenericService<Produto>{
	
	public ProdutoService() {
		super(Produto.class);
	}
	
	public List<Produto> retornaProdutosEmBaixa() {
		List<Produto> produtos = new ArrayList<Produto>();
		final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();	
    	final CriteriaQuery<Produto> cq = cb.createQuery(Produto.class);
    	final Root<Produto> rProduto = cq.from(Produto.class);
		
		cq.select(rProduto);
		cq.where(cb.lessThan(rProduto.<Integer>get("quantidade"), 5));
    	produtos = getEntityManager().createQuery(cq).getResultList();
		return produtos;
	}
	
	public List<Produto> retornaProdutosFiltradosPorNome(String nomeProduto) {
		List<Produto> produtos = new ArrayList<Produto>();
		final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();	
    	final CriteriaQuery<Produto> cq = cb.createQuery(Produto.class);
    	final Root<Produto> rProduto = cq.from(Produto.class);
		
		cq.select(rProduto);
		cq.where(cb.like(rProduto.<String>get("nome"), "%".concat(nomeProduto).concat("%")));
    	produtos = getEntityManager().createQuery(cq).getResultList();
		return produtos;
	}
	
	public Produto obtemPorCodigo(String codigo){
		final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();	
    	final CriteriaQuery<Produto> cq = cb.createQuery(Produto.class);
    	final Root<Produto> rProduto = cq.from(Produto.class);
		
		cq.select(rProduto);
		cq.where(cb.equal(rProduto.<String>get("codigo"), codigo));
		Produto produto = getEntityManager().createQuery(cq).getSingleResult();
		return produto;	
	}

}
