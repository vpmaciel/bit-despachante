package erp.escritorio.veiculo.seguro;

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

final class SeguroImp implements SeguroDao {

	@Override
	public void deletarRegistro(Seguro seguro) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(Seguro.class, seguro.getId()));
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
	public Collection<Seguro> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Seguro> seguroList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Seguro T order by T.nome", Seguro.class);
			seguroList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return seguroList;
	}

	@Override
	public Seguro getRegistro(Seguro seguro) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			seguro = entityManager.find(Seguro.class, seguro.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return seguro;
	}

	@Override
	public Collection<Seguro> pesquisarRegistro(Seguro seguro) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Seguro> seguroList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Seguro> criteriaQuery = criteriaBuilder.createQuery(Seguro.class);
			Root<Seguro> rootSeguro = criteriaQuery.from(Seguro.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (seguro.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootSeguro.get("id"), seguro.getId()));
			}
			if ((seguro.getNome() != null) && (seguro.getNome().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootSeguro.get("nome"), "%" + seguro.getNome() + "%"));
			}

			criteriaQuery.select(rootSeguro).where(predicateList.toArray(new Predicate[] {}));
			seguroList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return seguroList;
	}

	@Override
	public void salvarRegistro(Seguro seguro) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(seguro);
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
