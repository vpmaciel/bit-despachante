package erp.financeiro.vendas.produto;

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

final class VendaProdutoImp implements VendaProdutoDao {

	@Override
	public void deletarRegistro(VendaProduto vendaProduto) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(VendaProduto.class, vendaProduto.getId()));
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
	public Collection<VendaProduto> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<VendaProduto> vendaProdutoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from VendaProduto T order by T.nome", VendaProduto.class);
			vendaProdutoList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return vendaProdutoList;
	}

	@Override
	public VendaProduto getRegistro(VendaProduto vendaProduto) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			vendaProduto = entityManager.find(VendaProduto.class, vendaProduto.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return vendaProduto;
	}

	@Override
	public Collection<VendaProduto> pesquisarRegistro(VendaProduto vendaProduto) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<VendaProduto> vendaProdutoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<VendaProduto> criteriaQuery = criteriaBuilder.createQuery(VendaProduto.class);
			Root<VendaProduto> rootVendaProduto = criteriaQuery.from(VendaProduto.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (vendaProduto.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootVendaProduto.get("id"), vendaProduto.getId()));
			}
			// if ((vendaProduto.getProduto() != null) &&
			// (vendaProduto.getProduto().length() > 0)) {
			// predicateList.add(criteriaBuilder.like(rootVendaProduto.get("nome"), "%" +
			// vendaProduto.getProduto() + "%"));
			// }

			criteriaQuery.select(rootVendaProduto).where(predicateList.toArray(new Predicate[] {}));
			vendaProdutoList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return vendaProdutoList;
	}

	@Override
	public void salvarRegistro(VendaProduto vendaProduto) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(vendaProduto);
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
