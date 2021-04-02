package erp.negocio.seguradora;

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

final class SeguradoraImp implements SeguradoraDao {

	@Override
	public void deletarRegistro(Seguradora seguradora) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(Seguradora.class, seguradora.getId()));
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
	public Collection<Seguradora> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Seguradora> seguradoraList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Seguradora T order by T.nome", Seguradora.class);
			seguradoraList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return seguradoraList;
	}

	@Override
	public Seguradora getRegistro(Seguradora seguradora) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			seguradora = entityManager.find(Seguradora.class, seguradora.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return seguradora;
	}

	@Override
	public Collection<Seguradora> pesquisarRegistro(Seguradora seguradora) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Seguradora> seguradoraList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Seguradora> criteriaQuery = criteriaBuilder.createQuery(Seguradora.class);
			Root<Seguradora> rootSeguradora = criteriaQuery.from(Seguradora.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (seguradora.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootSeguradora.get("id"), seguradora.getId()));
			}
			if ((seguradora.getNome() != null) && (seguradora.getNome().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootSeguradora.get("nome"), "%" + seguradora.getNome() + "%"));
			}

			criteriaQuery.select(rootSeguradora).where(predicateList.toArray(new Predicate[] {}));
			seguradoraList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return seguradoraList;
	}

	@Override
	public void salvarRegistro(Seguradora seguradora) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(seguradora);
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
