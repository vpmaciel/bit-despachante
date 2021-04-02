package erp.sistema.dados;

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

final class DadosImp implements DadosDao {

	@Override
	public void deletarRegistro(Dados contador) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(entityManager.find(Dados.class, contador.getId()));
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
	public Collection<Dados> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Dados> contadorList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Dados T order by T.nome", Dados.class);
			contadorList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return contadorList;
	}

	@Override
	public Dados getRegistro(Dados contador) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			contador = entityManager.find(Dados.class, contador.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return contador;
	}

	@Override
	public Collection<Dados> pesquisarRegistro(Dados contador) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Dados> contadorList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Dados> criteriaQuery = criteriaBuilder.createQuery(Dados.class);
			Root<Dados> rootDados = criteriaQuery.from(Dados.class);
			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (contador.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootDados.get("id"), contador.getId()));
			}
			if ((contador.getCnpj() != null) && (contador.getCnpj().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDados.get("cnpj"), "%" + contador.getCnpj() + "%"));
			}
			if ((contador.getEmail() != null) && (contador.getEmail().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDados.get("email"), "%" + contador.getEmail() + "%"));
			}
			if ((contador.getFone1() != null) && (contador.getFone1().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDados.get("fone1"), "%" + contador.getFone1() + "%"));
			}
			if ((contador.getNome() != null) && (contador.getNome().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDados.get("nome"), "%" + contador.getNome() + "%"));
			}
			criteriaQuery.select(rootDados).where(predicateList.toArray(new Predicate[] {}));
			contadorList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return contadorList;
	}

	@Override
	public void salvarRegistro(Dados contador) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(contador);
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