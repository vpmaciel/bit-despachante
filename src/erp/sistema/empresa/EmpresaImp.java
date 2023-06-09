package erp.sistema.empresa;

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

final class EmpresaImp implements EmpresaDao {

	@Override
	public void deletarRegistro(Empresa empresa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(entityManager.find(Empresa.class, empresa.getId()));
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
	public Collection<Empresa> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Empresa> usuarioList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Usuario T order by T.nome", Empresa.class);
			usuarioList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return usuarioList;
	}

	@Override
	public Empresa getRegistro(Empresa empresa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			empresa = entityManager.find(Empresa.class, empresa.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return empresa;
	}

	@Override
	public boolean isRegistroValido(Empresa empresa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Empresa> usuarioList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Empresa> criteriaQuery = criteriaBuilder.createQuery(Empresa.class);
			Root<Empresa> rootUsuario = criteriaQuery.from(Empresa.class);

			List<Predicate> predicateList = new ArrayList<>();

			if ((empresa.getNome() != null) && !empresa.getNome().equals("")) {
				predicateList.add(criteriaBuilder.equal(rootUsuario.get("nome"), empresa.getNome()));
			}
			if ((empresa.getSenha() != null) && !empresa.getSenha().equals("")) {
				predicateList.add(criteriaBuilder.equal(rootUsuario.get("senha"), empresa.getSenha()));
			}
			criteriaQuery.select(rootUsuario).where(predicateList.toArray(new Predicate[] {}));

			usuarioList = entityManager.createQuery(criteriaQuery).getResultList();

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return usuarioList.size() == 1;
	}

	@Override
	public Collection<Empresa> pesquisarRegistro(Empresa empresa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Empresa> usuarioList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Empresa> criteriaQuery = criteriaBuilder.createQuery(Empresa.class);
			Root<Empresa> rootUsuario = criteriaQuery.from(Empresa.class);
			List<Predicate> predicateList = new ArrayList<>();
			if (empresa.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootUsuario.get("id"), empresa.getId()));
			}
			if ((empresa.getNome() != null) && (empresa.getNome().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootUsuario.get("nome"), empresa.getNome()));
			}
			if ((empresa.getSenha() != null) && (empresa.getSenha().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootUsuario.get("senha"), empresa.getSenha()));
			}
			criteriaQuery.select(rootUsuario).where(predicateList.toArray(new Predicate[] {}));
			usuarioList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return usuarioList;
	}

	@Override
	public void salvarRegistro(Empresa empresa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(empresa);
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