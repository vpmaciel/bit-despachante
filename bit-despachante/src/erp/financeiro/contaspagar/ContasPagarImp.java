package erp.financeiro.contaspagar;

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

final class ContasPagarImp implements ContasPagarDao {

	@Override
	public void deletarRegistro(ContasPagar contasPagar) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(ContasPagar.class, contasPagar.getId()));
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
	public Collection<ContasPagar> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<ContasPagar> contasPagarList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from ContasPagar T order by T.nome", ContasPagar.class);
			contasPagarList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return contasPagarList;
	}

	@Override
	public ContasPagar getRegistro(ContasPagar contasPagar) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			contasPagar = entityManager.find(ContasPagar.class, contasPagar.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return contasPagar;
	}

	@Override
	public Collection<ContasPagar> pesquisarRegistro(ContasPagar contasPagar) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<ContasPagar> contasPagarList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ContasPagar> criteriaQuery = criteriaBuilder.createQuery(ContasPagar.class);
			Root<ContasPagar> rootContasPagar = criteriaQuery.from(ContasPagar.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (contasPagar.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootContasPagar.get("id"), contasPagar.getId()));
			}
			if ((contasPagar.getDescricao() != null) && (contasPagar.getDescricao().length() > 0)) {
				predicateList
						.add(criteriaBuilder.like(rootContasPagar.get("nome"), "%" + contasPagar.getDescricao() + "%"));
			}

			criteriaQuery.select(rootContasPagar).where(predicateList.toArray(new Predicate[] {}));
			contasPagarList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return contasPagarList;
	}

	@Override
	public void salvarRegistro(ContasPagar contasPagar) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(contasPagar);
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
