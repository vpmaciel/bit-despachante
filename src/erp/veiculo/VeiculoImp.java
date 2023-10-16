package erp.veiculo;

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

final class VeiculoImp implements VeiculoDao {

	@Override
	public void deletarRegistro(Veiculo marca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(entityManager.find(Veiculo.class, marca.getId()));
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
	public Collection<Veiculo> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Veiculo> marcaList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Marca T order by T.descricao", Veiculo.class);
			marcaList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return marcaList;
	}

	@Override
	public Veiculo getRegistro(Veiculo marca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			marca = entityManager.find(Veiculo.class, marca.getId());
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
	public boolean isRegistroValido(Veiculo marca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Veiculo> marcaList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Veiculo> criteriaQuery = criteriaBuilder.createQuery(Veiculo.class);
			Root<Veiculo> rootMarca = criteriaQuery.from(Veiculo.class);

			List<Predicate> predicateList = new ArrayList<>();
			
			if ((marca.getCpfCnpjCliente() != null) && !(marca.getCpfCnpjCliente().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootMarca.get("cpfCnpjCliente"), marca.getCpfCnpjCliente()));
			}

			if (marca.getData() != null) {
				predicateList.add(criteriaBuilder.equal(rootMarca.get("data"), marca.getData()));
			}
			
			if ((marca.getDescricao() != null) && !(marca.getDescricao().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootMarca.get("descricao"), marca.getDescricao()));
			}			
			
			if ((marca.getNomeCliente() != null) && !(marca.getNomeCliente().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootMarca.get("nomeCliente"), marca.getDescricao()));
			}
			
			if ((marca.getPlaca() != null) && !(marca.getPlaca().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootMarca.get("placa"), marca.getDescricao()));
			}
			
			if ((marca.getTelefoneCliente() != null) && !(marca.getTelefoneCliente().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootMarca.get("telefoneCliente"), marca.getTelefoneCliente()));
			}
			
			predicateList.add(criteriaBuilder.equal(rootMarca.get("valor"), marca.getValor()));
			
						
			criteriaQuery.select(rootMarca).where(predicateList.toArray(new Predicate[] {}));

			marcaList = entityManager.createQuery(criteriaQuery).getResultList();

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return marcaList.size() == 1;
	}

	@Override
	public Collection<Veiculo> pesquisarRegistro(Veiculo marca) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Veiculo> marcaList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Veiculo> criteriaQuery = criteriaBuilder.createQuery(Veiculo.class);
			Root<Veiculo> rootMarca = criteriaQuery.from(Veiculo.class);
			List<Predicate> predicateList = new ArrayList<>();			
			if ((marca.getDescricao() != null) && (marca.getDescricao().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootMarca.get("descricao"), marca.getDescricao()));
			}			
			criteriaQuery.select(rootMarca).where(predicateList.toArray(new Predicate[] {}));
			marcaList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return marcaList;
	}

	@Override
	public void salvarRegistro(Veiculo marca) {
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