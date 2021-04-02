package erp.negocio.fornecedor;

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

final class FornecedorImp implements FornecedorDao {

	@Override
	public void deletarRegistro(Fornecedor fornecedor) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(entityManager.find(Fornecedor.class, fornecedor.getId()));
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
	public Collection<Fornecedor> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Fornecedor> fornecedorList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Fornecedor T order by T.nomeFantasia",
					Fornecedor.class);
			fornecedorList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return fornecedorList;
	}

	@Override
	public Fornecedor getRegistro(Fornecedor fornecedor) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			fornecedor = entityManager.find(Fornecedor.class, fornecedor.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return fornecedor;
	}

	@Override
	public Collection<Fornecedor> pesquisarRegistro(Fornecedor fornecedor) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Fornecedor> fornecedorList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Fornecedor> criteriaQuery = criteriaBuilder.createQuery(Fornecedor.class);
			Root<Fornecedor> rootFornecedor = criteriaQuery.from(Fornecedor.class);
			List<Predicate> predicateList = new ArrayList<Predicate>();
			if (fornecedor.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootFornecedor.get("id"), fornecedor.getId()));
			}
			if ((fornecedor.getEnderecoBairro() != null) && (fornecedor.getEnderecoBairro().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("enderecoBairro"),
						"%" + fornecedor.getEnderecoBairro() + "%"));
			}
			if ((fornecedor.getCapitalSocial() != null) && (fornecedor.getCapitalSocial().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("capitalSocial"),
						"%" + fornecedor.getCapitalSocial() + "%"));
			}
			if ((fornecedor.getEnderecoCep() != null) && (fornecedor.getEnderecoCep().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("enderecoCep"),
						"%" + fornecedor.getEnderecoCep() + "%"));
			}
			if ((fornecedor.getEnderecoCidade() != null) && (fornecedor.getEnderecoCidade().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("enderecoCidade"),
						"%" + fornecedor.getEnderecoCidade() + "%"));
			}
			if ((fornecedor.getCpf() != null) && (fornecedor.getCpf().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("cpf"), "%" + fornecedor.getCpf() + "%"));
			}
			if ((fornecedor.getCnpj() != null) && (fornecedor.getCnpj().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("cnpj"), "%" + fornecedor.getCnpj() + "%"));
			}
			if ((fornecedor.getEnderecoComplemento() != null) && (fornecedor.getEnderecoComplemento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("enderecoComplemento"),
						"%" + fornecedor.getEnderecoComplemento() + "%"));
			}
			if ((fornecedor.getEmail() != null) && (fornecedor.getEmail().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("email"), "%" + fornecedor.getEmail() + "%"));
			}
			if ((fornecedor.getEnderecoEstado() != null) && (fornecedor.getEnderecoEstado().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("enderecoEstado"),
						"%" + fornecedor.getEnderecoEstado() + "%"));
			}
			if ((fornecedor.getFax() != null) && (fornecedor.getFax().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("fax"), "%" + fornecedor.getFax() + "%"));
			}
			if ((fornecedor.getFone1() != null) && (fornecedor.getFone1().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("fone1"), "%" + fornecedor.getFone1() + "%"));
			}
			if ((fornecedor.getFone2() != null) && (fornecedor.getFone2().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("fone2"), "%" + fornecedor.getFone2() + "%"));
			}
			if ((fornecedor.getEnderecoLogradouro() != null) && (fornecedor.getEnderecoLogradouro().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("enderecoLogradouro"),
						"%" + fornecedor.getEnderecoLogradouro() + "%"));
			}
			if ((fornecedor.getNomeFantasia() != null) && (fornecedor.getNomeFantasia().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("nomeFantasia"),
						"%" + fornecedor.getNomeFantasia() + "%"));
			}
			if ((fornecedor.getEnderecoPais() != null) && (fornecedor.getEnderecoPais().length() > 0)) {
				predicateList.add(
						criteriaBuilder.like(rootFornecedor.get("pais"), "%" + fornecedor.getEnderecoPais() + "%"));
			}
			if ((fornecedor.getRazaoSocial() != null) && (fornecedor.getRazaoSocial().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("razaoSocial"),
						"%" + fornecedor.getRazaoSocial() + "%"));
			}
			if ((fornecedor.getTipoEmpresa() != null) && (fornecedor.getTipoEmpresa().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootFornecedor.get("tipoEmpresa"),
						"%" + fornecedor.getTipoEmpresa() + "%"));
			}
			criteriaQuery.select(rootFornecedor).where(predicateList.toArray(new Predicate[] {}));
			fornecedorList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return fornecedorList;
	}

	@Override
	public void salvarRegistro(Fornecedor fornecedor) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(fornecedor);
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