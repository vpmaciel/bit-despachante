package erp.sistema.pessoa;

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

final class PessoaImp implements PessoaDao {

	@Override
	public void deletarRegistro(Pessoa pessoa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(entityManager.find(Pessoa.class, pessoa.getId()));
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
	public Collection<Pessoa> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Pessoa> usuarioList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Pessoa T order by T.nome", Pessoa.class);
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
	public Pessoa getRegistro(Pessoa pessoa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			pessoa = entityManager.find(Pessoa.class, pessoa.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return pessoa;
	}

	@Override
	public boolean isRegistroValido(Pessoa pessoa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Pessoa> usuarioList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Pessoa> criteriaQuery = criteriaBuilder.createQuery(Pessoa.class);
			Root<Pessoa> rootPessoa = criteriaQuery.from(Pessoa.class);

			List<Predicate> predicateList = new ArrayList<>();

			if ((pessoa.getNome() != null) && !pessoa.getNome().equals("")) {
				predicateList.add(criteriaBuilder.equal(rootPessoa.get("nome"), pessoa.getNome()));
			}
			if ((pessoa.getSenha() != null) && !pessoa.getSenha().equals("")) {
				predicateList.add(criteriaBuilder.equal(rootPessoa.get("senha"), pessoa.getSenha()));
			}
			criteriaQuery.select(rootPessoa).where(predicateList.toArray(new Predicate[] {}));

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
	public Collection<Pessoa> pesquisarRegistro(Pessoa pessoa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Pessoa> usuarioList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Pessoa> criteriaQuery = criteriaBuilder.createQuery(Pessoa.class);
			Root<Pessoa> rootPessoa = criteriaQuery.from(Pessoa.class);
			List<Predicate> predicateList = new ArrayList<>();
			if (pessoa.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootPessoa.get("id"), pessoa.getId()));
			}
			if ((pessoa.getNome() != null) && (pessoa.getNome().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootPessoa.get("nome"), pessoa.getNome()));
			}
			if ((pessoa.getSenha() != null) && (pessoa.getSenha().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootPessoa.get("senha"), pessoa.getSenha()));
			}
			criteriaQuery.select(rootPessoa).where(predicateList.toArray(new Predicate[] {}));
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
	public void salvarRegistro(Pessoa pessoa) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(pessoa);
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