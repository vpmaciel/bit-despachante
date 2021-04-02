package erp.financeiro.vendas.servico;

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

final class VendaServicoImp implements VendaServicoDao {

	@Override
	public void deletarRegistro(VendaServico vendaServico) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(VendaServico.class, vendaServico.getId()));
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
	public Collection<VendaServico> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<VendaServico> vendaServicoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from VendaServico T", VendaServico.class);
			vendaServicoList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return vendaServicoList;
	}

	@Override
	public VendaServico getRegistro(VendaServico vendaServico) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			vendaServico = entityManager.find(VendaServico.class, vendaServico.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return vendaServico;
	}

	@Override
	public Collection<VendaServico> pesquisarRegistro(VendaServico vendaServico) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<VendaServico> vendaServicoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<VendaServico> criteriaQuery = criteriaBuilder.createQuery(VendaServico.class);
			Root<VendaServico> rootVendaServico = criteriaQuery.from(VendaServico.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (vendaServico.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootVendaServico.get("id"), vendaServico.getId()));
			}
			if ((vendaServico.getUsuario().getNome() != null) && (vendaServico.getUsuario().getNome().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootVendaServico.get("usuario"),
						"%" + vendaServico.getUsuario().getNome() + "%"));
			}

			criteriaQuery.select(rootVendaServico).where(predicateList.toArray(new Predicate[] {}));
			vendaServicoList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return vendaServicoList;
	}

	@Override
	public void salvarRegistro(VendaServico vendaServico) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(vendaServico);
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
