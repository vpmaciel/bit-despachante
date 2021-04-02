package erp.negocio.produto.marca;

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

final class ProdutoMarcaImp implements ProdutoMarcaDao {

	@Override
	public void deletarRegistro(ProdutoMarca produtoMarca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(ProdutoMarca.class, produtoMarca.getId()));
			entityTransaction.commit();
		} catch (Exception exception) {
			exception.printStackTrace();
			entityTransaction.rollback();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<ProdutoMarca> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<ProdutoMarca> produtoMarcaList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from ProdutoMarca T order by T.nome", ProdutoMarca.class);
			produtoMarcaList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return produtoMarcaList;
	}

	@Override
	public ProdutoMarca getRegistro(ProdutoMarca produtoMarca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			produtoMarca = entityManager.find(ProdutoMarca.class, produtoMarca.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return produtoMarca;
	}

	@Override
	public Collection<ProdutoMarca> pesquisarRegistro(ProdutoMarca produtoMarca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<ProdutoMarca> produtoMarcaList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProdutoMarca> criteriaQuery = criteriaBuilder.createQuery(ProdutoMarca.class);
			Root<ProdutoMarca> rootProdutoMarca = criteriaQuery.from(ProdutoMarca.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (produtoMarca.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootProdutoMarca.get("id"), produtoMarca.getId()));
			}
			if ((produtoMarca.getNome() != null) && (produtoMarca.getNome().length() > 0)) {
				predicateList
						.add(criteriaBuilder.like(rootProdutoMarca.get("nome"), "%" + produtoMarca.getNome() + "%"));
			}

			criteriaQuery.select(rootProdutoMarca).where(predicateList.toArray(new Predicate[] {}));
			produtoMarcaList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return produtoMarcaList;
	}

	@Override
	public void salvarRegistro(ProdutoMarca produtoMarca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(produtoMarca);
			entityTransaction.commit();
		} catch (Exception exception) {
			entityTransaction.rollback();
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}

}
