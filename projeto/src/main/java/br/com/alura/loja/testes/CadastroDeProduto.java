package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		cadastrarProduto();
		Long id = 1l;
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto p = produtoDao.buscarPorId(id);
		System.out.println(p.getPreco());
		
//		List<Produto> todos = produtoDao.buscarPorNome("Xiaomi Redmi");
		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("Celulares");
		todos.forEach(p2 -> System.out.println(p2.getNome()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
		System.out.println("Preço do produto: " + precoDoProduto);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("Celulares");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal(800), celulares);
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();

		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();
//				
//		em.persist(celulares);
//		celulares.setNome("XPTO");
//		
//		em.flush(); // Manda a alteração para o database, mas sem commit.
//		em.clear(); // Todas as entidades mudam para o estado Detached.
//		
//		// Criar uma entidade no estado Managed a partir do objeto celulares. 
//		celulares = em.merge(celulares); 
//		celulares.setNome("1234"); // Segunda alteração na entidade na mesma transação.
//		
//		em.flush();
//		
//		em.remove(celulares);
//		em.flush();
	}
}
