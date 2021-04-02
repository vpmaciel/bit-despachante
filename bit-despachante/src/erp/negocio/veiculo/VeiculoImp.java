package erp.negocio.veiculo;

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
	public void deletarRegistro(Veiculo veiculo) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(entityManager.find(Veiculo.class, veiculo.getId()));
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
		List<Veiculo> veiculoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Veiculo T order by T.id", Veiculo.class);
			veiculoList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return veiculoList;
	}

	@Override
	public Veiculo getRegistro(Veiculo veiculo) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			veiculo = entityManager.find(Veiculo.class, veiculo.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return veiculo;
	}

	@Override
	public Collection<Veiculo> pesquisarRegistro(Veiculo veiculo) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Veiculo> veiculoList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Veiculo> criteriaQuery = criteriaBuilder.createQuery(Veiculo.class);
			Root<Veiculo> rootVeiculo = criteriaQuery.from(Veiculo.class);
			List<Predicate> predicateList = new ArrayList<Predicate>();
			if (veiculo.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootVeiculo.get("id"), veiculo.getId()));
			}
			if ((veiculo.getAnoFabricacao() != null) && (veiculo.getAnoFabricacao().length() > 0)) {
				predicateList.add(
						criteriaBuilder.like(rootVeiculo.get("anoFabricacao"), "%" + veiculo.getAnoFabricacao() + "%"));
			}
			if ((veiculo.getAnoModelo() != null) && (veiculo.getAnoModelo().length() > 0)) {
				predicateList
						.add(criteriaBuilder.like(rootVeiculo.get("anoModelo"), "%" + veiculo.getAnoModelo() + "%"));
			}			
			if ((veiculo.getChassi() != null) && (veiculo.getChassi().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootVeiculo.get("chassi"), "%" + veiculo.getChassi() + "%"));
			}				
			if ((veiculo.getDataCompra() != null) && (veiculo.getDataCompra().length() > 0)) {
				predicateList
						.add(criteriaBuilder.like(rootVeiculo.get("dataCompra"), "%" + veiculo.getDataCompra() + "%"));
			}
			if ((veiculo.getDataVenda() != null) && (veiculo.getDataVenda().length() > 0)) {
				predicateList
						.add(criteriaBuilder.like(rootVeiculo.get("dataVenda"), "%" + veiculo.getDataVenda() + "%"));
			}			
			if ((veiculo.getFabricacao() != null) && (veiculo.getFabricacao().length() > 0)) {
				predicateList.add(
						criteriaBuilder.like(rootVeiculo.get("fabricacao"), "%" + veiculo.getFabricacao() + "%"));
			}
			if (veiculo.getMarca() != null) {
				predicateList.add(criteriaBuilder.equal(rootVeiculo.get("marca"), veiculo.getMarca()));
			}
			if (veiculo.getModelo() != null) {
				predicateList.add(criteriaBuilder.equal(rootVeiculo.get("modelo"), veiculo.getModelo()));
			}
			if ((veiculo.getNumeroMotor() != null) && (veiculo.getNumeroMotor().length() > 0)) {
				predicateList.add(
						criteriaBuilder.like(rootVeiculo.get("numeroMotor"), "%" + veiculo.getNumeroMotor() + "%"));
			}
			if ((veiculo.getPlaca() != null) && (veiculo.getPlaca().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootVeiculo.get("placa"), "%" + veiculo.getPlaca() + "%"));
			}
			if ((veiculo.getProprietarioNome() != null) && (veiculo.getProprietarioNome().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootVeiculo.get("proprietarioNome"),
						"%" + veiculo.getProprietarioNome() + "%"));
			}
			if ((veiculo.getRenavam() != null) && (veiculo.getRenavam().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootVeiculo.get("renavam"), "%" + veiculo.getRenavam() + "%"));
			}
			if ((veiculo.getRestricoes() != null) && (veiculo.getRestricoes().length() > 0)) {
				predicateList
						.add(criteriaBuilder.like(rootVeiculo.get("restricoes"), "%" + veiculo.getRestricoes() + "%"));
			}
			if ((veiculo.getTipo() != null) && (veiculo.getTipo().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootVeiculo.get("tipo"), "%" + veiculo.getTipo() + "%"));
			}
			if (veiculo.getValorCompra() > 0) {
				predicateList.add(
						criteriaBuilder.lessThanOrEqualTo(rootVeiculo.get("valorCompra"), veiculo.getValorCompra()));
			}
			if (veiculo.getValorVenda() > 0) {
				predicateList
						.add(criteriaBuilder.lessThanOrEqualTo(rootVeiculo.get("valorVenda"), veiculo.getValorVenda()));
			}

			criteriaQuery.select(rootVeiculo).where(predicateList.toArray(new Predicate[] {}));
			veiculoList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return veiculoList;
	}

	@Override
	public void salvarRegistro(Veiculo veiculo) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(veiculo);
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