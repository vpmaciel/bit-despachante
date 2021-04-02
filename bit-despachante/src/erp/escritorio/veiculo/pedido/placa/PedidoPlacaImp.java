package erp.escritorio.veiculo.pedido.placa;

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

final class PedidoPlacaImp implements PedidoPlacaDao {

	@Override
	public void deletarRegistro(PedidoPlaca pedidoPlaca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(PedidoPlaca.class, pedidoPlaca.getId()));
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
	public Collection<PedidoPlaca> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<PedidoPlaca> pedidoPlacaList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from PedidoPlaca T order by T.nome", PedidoPlaca.class);
			pedidoPlacaList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return pedidoPlacaList;
	}

	@Override
	public PedidoPlaca getRegistro(PedidoPlaca pedidoPlaca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			pedidoPlaca = entityManager.find(PedidoPlaca.class, pedidoPlaca.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return pedidoPlaca;
	}

	@Override
	public Collection<PedidoPlaca> pesquisarRegistro(PedidoPlaca pedidoPlaca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<PedidoPlaca> pedidoPlacaList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<PedidoPlaca> criteriaQuery = criteriaBuilder.createQuery(PedidoPlaca.class);
			Root<PedidoPlaca> rootPedidoPlaca = criteriaQuery.from(PedidoPlaca.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (pedidoPlaca.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootPedidoPlaca.get("id"), pedidoPlaca.getId()));
			}
			if ((pedidoPlaca.getNome() != null) && (pedidoPlaca.getNome().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootPedidoPlaca.get("nome"), "%" + pedidoPlaca.getNome() + "%"));
			}

			criteriaQuery.select(rootPedidoPlaca).where(predicateList.toArray(new Predicate[] {}));
			pedidoPlacaList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return pedidoPlacaList;
	}

	@Override
	public void salvarRegistro(PedidoPlaca pedidoPlaca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(pedidoPlaca);
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
