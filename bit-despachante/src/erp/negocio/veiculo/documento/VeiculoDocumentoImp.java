package erp.negocio.veiculo.documento;

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

final class VeiculoDocumentoImp implements VeiculoDocumentoDao {

	@Override
	public void deletarRegistro(VeiculoDocumento veiculoDocumento) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(entityManager.find(VeiculoDocumento.class, veiculoDocumento.getId()));
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
	public Collection<VeiculoDocumento> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<VeiculoDocumento> veiculoDocumentoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from VeiculoDocumento T order by T.funcionario",
					VeiculoDocumento.class);
			veiculoDocumentoList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return veiculoDocumentoList;
	}

	@Override
	public VeiculoDocumento getRegistro(VeiculoDocumento veiculoDocumento) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			veiculoDocumento = entityManager.find(VeiculoDocumento.class, veiculoDocumento.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return veiculoDocumento;
	}

	@Override
	public Collection<VeiculoDocumento> pesquisarRegistro(VeiculoDocumento veiculoDocumento) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<VeiculoDocumento> veiculoDocumentoList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<VeiculoDocumento> criteriaQuery = criteriaBuilder.createQuery(VeiculoDocumento.class);
			Root<VeiculoDocumento> rootVeiculoDocumento = criteriaQuery.from(VeiculoDocumento.class);
			List<Predicate> predicateList = new ArrayList<Predicate>();

			if ((veiculoDocumento.getCnpjRecebedorVeiculoDocumento() != null)
					&& (veiculoDocumento.getCnpjRecebedorVeiculoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootVeiculoDocumento.get("cnpjRecebedorVeiculoDocumento"),
						"%" + veiculoDocumento.getCnpjRecebedorVeiculoDocumento() + "%"));
			}
			if ((veiculoDocumento.getCpfRecebedorVeiculoDocumento() != null)
					&& (veiculoDocumento.getCpfRecebedorVeiculoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootVeiculoDocumento.get("cpfRecebedorVeiculoDocumento"),
						"%" + veiculoDocumento.getCpfRecebedorVeiculoDocumento() + "%"));
			}
			if ((veiculoDocumento.getDataDevolucaoVeiculoDocumento() != null)
					&& (veiculoDocumento.getDataDevolucaoVeiculoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootVeiculoDocumento.get("dataDevolucaoVeiculoDocumento"),
						"%" + veiculoDocumento.getDataDevolucaoVeiculoDocumento() + "%"));
			}
			if ((veiculoDocumento.getDataRecebimentoVeiculoDocumento() != null)
					&& (veiculoDocumento.getDataRecebimentoVeiculoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootVeiculoDocumento.get("dataRecebimentoVeiculoDocumento"),
						"%" + veiculoDocumento.getDataRecebimentoVeiculoDocumento() + "%"));
			}
			if ((veiculoDocumento.getLocalVeiculoDocumento() != null)
					&& (veiculoDocumento.getLocalVeiculoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootVeiculoDocumento.get("localVeiculoDocumento"),
						"%" + veiculoDocumento.getLocalVeiculoDocumento() + "%"));
			}
			if ((veiculoDocumento.getNomeRecebedorVeiculoDocumento() != null)
					&& (veiculoDocumento.getNomeRecebedorVeiculoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootVeiculoDocumento.get("nomeRecebedorVeiculoDocumento"),
						"%" + veiculoDocumento.getNomeRecebedorVeiculoDocumento() + "%"));
			}
			if ((veiculoDocumento.getRgNumeroRecebedorVeiculoDocumento() != null)
					&& (veiculoDocumento.getRgNumeroRecebedorVeiculoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootVeiculoDocumento.get("rgNumeroRecebedorVeiculoDocumento"),
						"%" + veiculoDocumento.getRgNumeroRecebedorVeiculoDocumento() + "%"));
			}
			if ((veiculoDocumento.getRgOrgaoEmisssorRecebedorVeiculoDocumento() != null)
					&& (veiculoDocumento.getRgOrgaoEmisssorRecebedorVeiculoDocumento().length() > 0)) {
				predicateList
						.add(criteriaBuilder.like(rootVeiculoDocumento.get("rgOrgaoEmisssorRecebedorVeiculoDocumento"),
								"%" + veiculoDocumento.getRgOrgaoEmisssorRecebedorVeiculoDocumento() + "%"));
			}
			if ((veiculoDocumento.getSituacaoVeiculoDocumento() != null)
					&& (veiculoDocumento.getSituacaoVeiculoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootVeiculoDocumento.get("situacaoVeiculoDocumento"),
						"%" + veiculoDocumento.getSituacaoVeiculoDocumento() + "%"));
			}

			criteriaQuery.select(rootVeiculoDocumento).where(predicateList.toArray(new Predicate[] {}));
			veiculoDocumentoList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return veiculoDocumentoList;
	}

	@Override
	public void salvarRegistro(VeiculoDocumento veiculoDocumento) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(veiculoDocumento);
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