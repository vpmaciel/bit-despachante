package erp.escritorio.produto.compra.lista;

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

final class CompraProdutoImp implements CompraProdutoDao {

	@Override
	public void deletarRegistro(CompraProduto compraProduto) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(CompraProduto.class, compraProduto.getId()));
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
	public Collection<CompraProduto> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<CompraProduto> compraProdutoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from CompraProduto T order by T.nome",
					CompraProduto.class);
			compraProdutoList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return compraProdutoList;
	}

	@Override
	public CompraProduto getRegistro(CompraProduto compraProduto) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			compraProduto = entityManager.find(CompraProduto.class, compraProduto.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return compraProduto;
	}

	@Override
	public Collection<CompraProduto> pesquisarRegistro(CompraProduto compraProduto) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<CompraProduto> compraProdutoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<CompraProduto> criteriaQuery = criteriaBuilder.createQuery(CompraProduto.class);
			Root<CompraProduto> rootCompraProduto = criteriaQuery.from(CompraProduto.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (compraProduto.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootCompraProduto.get("id"), compraProduto.getId()));
			}
			if ((compraProduto.getNome() != null) && (compraProduto.getNome().length() > 0)) {
				predicateList
						.add(criteriaBuilder.like(rootCompraProduto.get("nome"), "%" + compraProduto.getNome() + "%"));
			}

			criteriaQuery.select(rootCompraProduto).where(predicateList.toArray(new Predicate[] {}));
			compraProdutoList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return compraProdutoList;
	}

	@Override
	public void salvarRegistro(CompraProduto compraProduto) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(compraProduto);
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
