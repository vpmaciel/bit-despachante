package erp.sistema.atividade;

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

final class AtividadeImp implements AtividadeDao {

	@Override
	public void deletarRegistro(Atividade atividade) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(entityManager.find(Atividade.class, atividade.getId()));
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
	public Collection<Atividade> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Atividade> usuarioList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Usuario T order by T.nome", Atividade.class);
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
	public Atividade getRegistro(Atividade atividade) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			atividade = entityManager.find(Atividade.class, atividade.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return atividade;
	}

	@Override
	public boolean isRegistroValido(Atividade atividade) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Atividade> usuarioList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Atividade> criteriaQuery = criteriaBuilder.createQuery(Atividade.class);
			Root<Atividade> rootUsuario = criteriaQuery.from(Atividade.class);

			List<Predicate> predicateList = new ArrayList<>();

			if ((atividade.getNome() != null) && !atividade.getNome().equals("")) {
				predicateList.add(criteriaBuilder.equal(rootUsuario.get("nome"), atividade.getNome()));
			}
			if ((atividade.getSenha() != null) && !atividade.getSenha().equals("")) {
				predicateList.add(criteriaBuilder.equal(rootUsuario.get("senha"), atividade.getSenha()));
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
	public Collection<Atividade> pesquisarRegistro(Atividade atividade) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Atividade> usuarioList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Atividade> criteriaQuery = criteriaBuilder.createQuery(Atividade.class);
			Root<Atividade> rootUsuario = criteriaQuery.from(Atividade.class);
			List<Predicate> predicateList = new ArrayList<>();
			if (atividade.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootUsuario.get("id"), atividade.getId()));
			}
			if ((atividade.getNome() != null) && (atividade.getNome().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootUsuario.get("nome"), atividade.getNome()));
			}
			if ((atividade.getSenha() != null) && (atividade.getSenha().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootUsuario.get("senha"), atividade.getSenha()));
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
	public void salvarRegistro(Atividade atividade) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(atividade);
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