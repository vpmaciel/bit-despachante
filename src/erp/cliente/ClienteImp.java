package erp.cliente;

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

final class ClienteImp implements ClienteDao {

	@Override
	public void deletarRegistro(Cliente cliente) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(entityManager.find(Cliente.class, cliente.getId()));
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
	public Collection<Cliente> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Cliente> clienteList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Cliente T order by T.nome", Cliente.class);
			clienteList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return clienteList;
	}

	@Override
	public Cliente getRegistro(Cliente cliente) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			cliente = entityManager.find(Cliente.class, cliente.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return cliente;
	}

	@Override
	public Collection<Cliente> pesquisarRegistro(Cliente cliente) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Cliente> clienteList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
			Root<Cliente> rootCliente = criteriaQuery.from(Cliente.class);
			List<Predicate> predicateList = new ArrayList<>();

			if ((cliente.getCpfCnpj() != null) && (cliente.getCpfCnpj().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootCliente.get("cpfCnpj"), cliente.getCpfCnpj()));
			}

			if ((cliente.getEmail() != null) && (cliente.getEmail().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootCliente.get("email"), cliente.getEmail()));
			}

			if ((cliente.getNome() != null) && (cliente.getNome().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootCliente.get("nome"), cliente.getNome()));
			}

			if ((cliente.getTelefone() != null) && (cliente.getTelefone().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootCliente.get("telefone"), cliente.getTelefone()));
			}
			criteriaQuery.select(rootCliente).where(predicateList.toArray(new Predicate[] {}));
			clienteList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return clienteList;
	}

	@Override
	public void salvarRegistro(Cliente cliente) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(cliente);
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