package erp.financeiro.contasreceber;

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

final class ContasReceberImp implements ContasReceberDao {

	@Override
	public void deletarRegistro(ContasReceber contasReceber) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(ContasReceber.class, contasReceber.getId()));
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
	public Collection<ContasReceber> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<ContasReceber> contasReceberList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from ContasReceber T order by T.nome",
					ContasReceber.class);
			contasReceberList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return contasReceberList;
	}

	@Override
	public ContasReceber getRegistro(ContasReceber contasReceber) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			contasReceber = entityManager.find(ContasReceber.class, contasReceber.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return contasReceber;
	}

	@Override
	public Collection<ContasReceber> pesquisarRegistro(ContasReceber contasReceber) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<ContasReceber> contasReceberList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ContasReceber> criteriaQuery = criteriaBuilder.createQuery(ContasReceber.class);
			Root<ContasReceber> rootContasReceber = criteriaQuery.from(ContasReceber.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (contasReceber.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootContasReceber.get("id"), contasReceber.getId()));
			}
			if ((contasReceber.getDescricao() != null) && (contasReceber.getDescricao().length() > 0)) {
				predicateList.add(
						criteriaBuilder.like(rootContasReceber.get("nome"), "%" + contasReceber.getDescricao() + "%"));
			}

			criteriaQuery.select(rootContasReceber).where(predicateList.toArray(new Predicate[] {}));
			contasReceberList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return contasReceberList;
	}

	@Override
	public void salvarRegistro(ContasReceber contasReceber) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(contasReceber);
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
