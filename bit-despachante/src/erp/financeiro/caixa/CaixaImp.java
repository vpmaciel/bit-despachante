package erp.financeiro.caixa;

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

final class CaixaImp implements CaixaDao {

	@Override
	public void deletarRegistro(Caixa caixa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(Caixa.class, caixa.getId()));
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
	public Collection<Caixa> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Caixa> caixaList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Caixa T order by T.nome", Caixa.class);
			caixaList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return caixaList;
	}

	@Override
	public Caixa getRegistro(Caixa caixa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			caixa = entityManager.find(Caixa.class, caixa.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return caixa;
	}

	@Override
	public Collection<Caixa> pesquisarRegistro(Caixa caixa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Caixa> caixaList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Caixa> criteriaQuery = criteriaBuilder.createQuery(Caixa.class);
			Root<Caixa> rootCaixa = criteriaQuery.from(Caixa.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (caixa.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootCaixa.get("id"), caixa.getId()));
			}
			if ((caixa.getUsuario() != null) && (caixa.getUsuario().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCaixa.get("usuario"), "%" + caixa.getUsuario() + "%"));
			}

			criteriaQuery.select(rootCaixa).where(predicateList.toArray(new Predicate[] {}));
			caixaList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return caixaList;
	}

	@Override
	public void salvarRegistro(Caixa caixa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(caixa);
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
