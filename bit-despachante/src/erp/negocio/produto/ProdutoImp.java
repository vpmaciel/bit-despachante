package erp.negocio.produto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import erp.arquitetura.Jpa;

final class ProdutoImp implements ProdutoDao {

	@Override
	public void deletarRegistro(Produto produto) {
		EntityManager entityManager = Jpa.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.remove(entityManager.find(Produto.class, produto.getId()));
			entityTransaction.commit();
		} catch (Exception exception) {
			entityTransaction.rollback();
			exception.printStackTrace();
			throw exception;
		} finally {
			entityManager.close();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<Produto> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Produto> ProdutoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Produto T order by T.nome", Produto.class);
			ProdutoList = query.getResultList();
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return ProdutoList;
	}

	@Override
	public Produto getRegistro(Produto produto) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			produto = entityManager.find(Produto.class, produto.getId());

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			entityManager.close();
		}
		return produto;
	}

	@Override
	public Collection<Produto> pesquisarRegistro(Produto produto) {

		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Produto> ProdutoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
			Root<Produto> rootProduto = criteriaQuery.from(Produto.class);
			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (produto.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootProduto.get("id"), produto.getId()));
			}
			if ((produto.getNome() != null) && (produto.getNome().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootProduto.get("nome"), "%" + produto.getNome() + "%"));
			}
			if ((produto.getPreco() > 0)) {
				predicateList.add(criteriaBuilder.like(rootProduto.get("preco"), "%" + produto.getPreco() + "%"));
			}

			criteriaQuery.select(rootProduto).where(predicateList.toArray(new Predicate[] {}));
			ProdutoList = entityManager.createQuery(criteriaQuery).getResultList();
			entityTransaction.commit();

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			entityManager.close();
		}

		return ProdutoList;
	}

	@Override
	public void salvarRegistro(Produto produto) {

		EntityManager entityManager = Jpa.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.merge(produto);
			entityTransaction.commit();
		} catch (Exception exception) {
			exception.printStackTrace();
			entityTransaction.rollback();
			throw exception;
		} finally {
			entityManager.close();
		}
	}
}
