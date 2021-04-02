package erp.negocio.produto.unidade;

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

final class ProdutoUnidadeImp implements ProdutoUnidadeDao {

	@Override
	public void deletarRegistro(ProdutoUnidade produtoUnidade) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(ProdutoUnidade.class, produtoUnidade.getId()));
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
	public Collection<ProdutoUnidade> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<ProdutoUnidade> produtoUnidadeList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from ProdutoUnidade T order by T.nome",
					ProdutoUnidade.class);
			produtoUnidadeList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return produtoUnidadeList;
	}

	@Override
	public ProdutoUnidade getRegistro(ProdutoUnidade produtoUnidade) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			produtoUnidade = entityManager.find(ProdutoUnidade.class, produtoUnidade.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return produtoUnidade;
	}

	@Override
	public Collection<ProdutoUnidade> pesquisarRegistro(ProdutoUnidade produtoUnidade) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<ProdutoUnidade> produtoUnidadeList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProdutoUnidade> criteriaQuery = criteriaBuilder.createQuery(ProdutoUnidade.class);
			Root<ProdutoUnidade> rootProdutoUnidade = criteriaQuery.from(ProdutoUnidade.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (produtoUnidade.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootProdutoUnidade.get("id"), produtoUnidade.getId()));
			}
			if ((produtoUnidade.getNome() != null) && (produtoUnidade.getNome().length() > 0)) {
				predicateList.add(
						criteriaBuilder.like(rootProdutoUnidade.get("nome"), "%" + produtoUnidade.getNome() + "%"));
			}

			criteriaQuery.select(rootProdutoUnidade).where(predicateList.toArray(new Predicate[] {}));
			produtoUnidadeList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return produtoUnidadeList;
	}

	@Override
	public void salvarRegistro(ProdutoUnidade produtoUnidade) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(produtoUnidade);
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
