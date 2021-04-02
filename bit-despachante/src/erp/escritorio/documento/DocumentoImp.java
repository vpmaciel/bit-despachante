package erp.escritorio.documento;

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

final class DocumentoImp implements DocumentoDao {

	@Override
	public void deletarRegistro(Documento veiculoDocumento) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(entityManager.find(Documento.class, veiculoDocumento.getId()));
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
	public Collection<Documento> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Documento> veiculoDocumentoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Documento T order by T.funcionario",
					Documento.class);
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
	public Documento getRegistro(Documento veiculoDocumento) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			veiculoDocumento = entityManager.find(Documento.class, veiculoDocumento.getId());
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
	public Collection<Documento> pesquisarRegistro(Documento veiculoDocumento) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Documento> veiculoDocumentoList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Documento> criteriaQuery = criteriaBuilder.createQuery(Documento.class);
			Root<Documento> rootDocumento = criteriaQuery.from(Documento.class);
			List<Predicate> predicateList = new ArrayList<Predicate>();

			if ((veiculoDocumento.getDescricao() != null) && (veiculoDocumento.getDescricao().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("descricao"),
						"%" + veiculoDocumento.getAnoDevolucaoDocumento() + "%"));
			}
			if ((veiculoDocumento.getTipoDocumento() != null) && (veiculoDocumento.getTipoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("tipoDocumento"),
						"%" + veiculoDocumento.getAnoDevolucaoDocumento() + "%"));
			}
			if ((veiculoDocumento.getNomeProprietario() != null)
					&& (veiculoDocumento.getNomeProprietario().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("nomeProprietario"),
						"%" + veiculoDocumento.getAnoDevolucaoDocumento() + "%"));
			}
			if ((veiculoDocumento.getAnoDevolucaoDocumento() != null)
					&& (veiculoDocumento.getAnoDevolucaoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("anoDevolucaoDocumento"),
						"%" + veiculoDocumento.getAnoDevolucaoDocumento() + "%"));
			}
			if ((veiculoDocumento.getAnoRecebimentoDocumento() != null)
					&& (veiculoDocumento.getAnoRecebimentoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("anoRecebimentoDocumento"),
						"%" + veiculoDocumento.getAnoDevolucaoDocumento() + "%"));
			}
			if ((veiculoDocumento.getCnpjRecebedorDocumento() != null)
					&& (veiculoDocumento.getCnpjRecebedorDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("cnpjRecebedorDocumento"),
						"%" + veiculoDocumento.getCnpjRecebedorDocumento() + "%"));
			}
			if ((veiculoDocumento.getCpfRecebedorDocumento() != null)
					&& (veiculoDocumento.getCpfRecebedorDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("cpfRecebedorDocumento"),
						"%" + veiculoDocumento.getCpfRecebedorDocumento() + "%"));
			}
			if ((veiculoDocumento.getDiaDevolucaoDocumento() != null)
					&& (veiculoDocumento.getDiaDevolucaoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootDocumento.get("dataDevolucaoDocumento"),
						"%" + veiculoDocumento.getDiaDevolucaoDocumento() + "%"));
			}
			if ((veiculoDocumento.getDiaRecebimentoDocumento() != null)
					&& (veiculoDocumento.getDiaRecebimentoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.equal(rootDocumento.get("dataRecebimentoDocumento"),
						"%" + veiculoDocumento.getDiaRecebimentoDocumento() + "%"));
			}
			if ((veiculoDocumento.getLocalDocumento() != null) && (veiculoDocumento.getLocalDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("localDocumento"),
						"%" + veiculoDocumento.getLocalDocumento() + "%"));
			}
			if ((veiculoDocumento.getMesDevolucaoDocumento() != null)
					&& (veiculoDocumento.getMesDevolucaoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("mesDevolucaoDocumento"),
						"%" + veiculoDocumento.getMesDevolucaoDocumento() + "%"));
			}
			if ((veiculoDocumento.getMesRecebimentoDocumento() != null)
					&& (veiculoDocumento.getMesRecebimentoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("mesRecebimentoDocumento"),
						"%" + veiculoDocumento.getMesRecebimentoDocumento() + "%"));
			}
			if ((veiculoDocumento.getNomeRecebedorDocumento() != null)
					&& (veiculoDocumento.getNomeRecebedorDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("nomeRecebedorDocumento"),
						"%" + veiculoDocumento.getNomeRecebedorDocumento() + "%"));
			}
			if ((veiculoDocumento.getRgNumeroRecebedorDocumento() != null)
					&& (veiculoDocumento.getRgNumeroRecebedorDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("rgNumeroRecebedorDocumento"),
						"%" + veiculoDocumento.getRgNumeroRecebedorDocumento() + "%"));
			}
			if ((veiculoDocumento.getRgOrgaoEmisssorRecebedorDocumento() != null)
					&& (veiculoDocumento.getRgOrgaoEmisssorRecebedorDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("rgOrgaoEmisssorRecebedorDocumento"),
						"%" + veiculoDocumento.getRgOrgaoEmisssorRecebedorDocumento() + "%"));
			}
			if ((veiculoDocumento.getSituacaoDocumento() != null)
					&& (veiculoDocumento.getSituacaoDocumento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootDocumento.get("situacaoDocumento"),
						"%" + veiculoDocumento.getSituacaoDocumento() + "%"));
			}

			criteriaQuery.select(rootDocumento).where(predicateList.toArray(new Predicate[] {}));
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
	public void salvarRegistro(Documento veiculoDocumento) {
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