package erp.negocio.produto.categoria;

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

final class ProdutoCategoriaImp implements ProdutoCategoriaDao {

	@Override
	public void deletarRegistro(ProdutoCategoria servico) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(ProdutoCategoria.class, servico.getId()));
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
	public Collection<ProdutoCategoria> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<ProdutoCategoria> servicoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from ProdutoCategoria T order by T.nome",
					ProdutoCategoria.class);
			servicoList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return servicoList;
	}

	@Override
	public ProdutoCategoria getRegistro(ProdutoCategoria servico) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			servico = entityManager.find(ProdutoCategoria.class, servico.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return servico;
	}

	@Override
	public Collection<ProdutoCategoria> pesquisarRegistro(ProdutoCategoria servico) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<ProdutoCategoria> servicoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProdutoCategoria> criteriaQuery = criteriaBuilder.createQuery(ProdutoCategoria.class);
			Root<ProdutoCategoria> rootProdutoCategoria = criteriaQuery.from(ProdutoCategoria.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (servico.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootProdutoCategoria.get("id"), servico.getId()));
			}
			if ((servico.getNome() != null) && (servico.getNome().length() > 0)) {
				predicateList
						.add(criteriaBuilder.like(rootProdutoCategoria.get("nome"), "%" + servico.getNome() + "%"));
			}

			criteriaQuery.select(rootProdutoCategoria).where(predicateList.toArray(new Predicate[] {}));
			servicoList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return servicoList;
	}

	@Override
	public void salvarRegistro(ProdutoCategoria servico) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(servico);
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
