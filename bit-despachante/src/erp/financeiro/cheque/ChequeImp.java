package erp.financeiro.cheque;

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

final class ChequeImp implements ChequeDao {

	@Override
	public void deletarRegistro(Cheque cheque) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(Cheque.class, cheque.getId()));
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
	public Collection<Cheque> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Cheque> chequeList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Cheque T order by T.nome", Cheque.class);
			chequeList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return chequeList;
	}

	@Override
	public Cheque getRegistro(Cheque cheque) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			cheque = entityManager.find(Cheque.class, cheque.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return cheque;
	}

	@Override
	public Collection<Cheque> pesquisarRegistro(Cheque cheque) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Cheque> chequeList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Cheque> criteriaQuery = criteriaBuilder.createQuery(Cheque.class);
			Root<Cheque> rootCheque = criteriaQuery.from(Cheque.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (cheque.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootCheque.get("id"), cheque.getId()));
			}
			if ((cheque.getNome() != null) && (cheque.getNome().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCheque.get("nome"), "%" + cheque.getNome() + "%"));
			}

			criteriaQuery.select(rootCheque).where(predicateList.toArray(new Predicate[] {}));
			chequeList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return chequeList;
	}

	@Override
	public void salvarRegistro(Cheque cheque) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(cheque);
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
