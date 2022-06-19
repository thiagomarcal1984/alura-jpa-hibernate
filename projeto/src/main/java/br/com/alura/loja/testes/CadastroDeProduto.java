package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		Categoria celulares = new Categoria("Celulares");
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();

		em.persist(celulares);
		celulares.setNome("XPTO");
		
		em.flush(); // Manda a alteração para o database, mas sem commit.
		em.clear(); // Todas as entidades mudam para o estado Detached.
		
		// Criar uma entidade no estado Managed a partir do objeto celulares. 
		celulares = em.merge(celulares); 
		celulares.setNome("1234"); // Segunda alteração na entidade na mesma transação.
		
		em.flush();
		
		em.remove(celulares);
		em.flush();
	}
}
