package erp.veiculo.marca;

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

final class MarcaImp implements MarcaDao {

	@Override
	public void deletarRegistro(Marca marca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(entityManager.find(Marca.class, marca.getId()));
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
	public Collection<Marca> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Marca> usuarioList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Marca T order by T.descricao", Marca.class);
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
	public Marca getRegistro(Marca marca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			marca = entityManager.find(Marca.class, marca.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return marca;
	}

	@Override
	public boolean isRegistroValido(Marca marca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Marca> usuarioList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Marca> criteriaQuery = criteriaBuilder.createQuery(Marca.class);
			Root<Marca> rootUsuario = criteriaQuery.from(Marca.class);

			List<Predicate> predicateList = new ArrayList<>();

			if ((marca.getDescricao() != null) && !(marca.getDescricao().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootUsuario.get("descricao"), marca.getDescricao()));
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
	public Collection<Marca> pesquisarRegistro(Marca marca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Marca> usuarioList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Marca> criteriaQuery = criteriaBuilder.createQuery(Marca.class);
			Root<Marca> rootMarca = criteriaQuery.from(Marca.class);
			List<Predicate> predicateList = new ArrayList<>();			
			if ((marca.getDescricao() != null) && !(marca.getDescricao().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootMarca.get("descricao"), marca.getDescricao()));
			}			
			criteriaQuery.select(rootMarca).where(predicateList.toArray(new Predicate[] {}));
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
	public void salvarRegistro(Marca marca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(marca);
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