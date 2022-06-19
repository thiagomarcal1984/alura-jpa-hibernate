package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;

public class CategoriaDao {
	private EntityManager em;
	
	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		/* For�ar o EntityManager a criar uma nova entidade gerenciada com 
		 * os dados de categoria: a atualiza��o ser� garantida n�o, pelo objeto 
		 * categoria, mas pelo objeto que for retornado de merge. O estado de
		 * categoria continuaria Detached; o estado da entidade retornada pelo
		 * m�todo merge ser� Managed.
		 */
		this.em.merge(categoria); 
	}
}
