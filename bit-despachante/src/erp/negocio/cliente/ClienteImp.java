package erp.negocio.cliente;

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

final class ClienteImp implements ClienteDao {

	@Override
	public void deletarRegistro(Cliente cliente) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(entityManager.find(Cliente.class, cliente.getId()));
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
	public Collection<Cliente> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Cliente> clienteList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Cliente T order by T.id", Cliente.class);
			clienteList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return clienteList;
	}

	@Override
	public Cliente getRegistro(Cliente cliente) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			cliente = entityManager.find(Cliente.class, cliente.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return cliente;
	}

	@Override
	public Collection<Cliente> pesquisarRegistro(Cliente cliente) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Cliente> clienteList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
			Root<Cliente> rootCliente = criteriaQuery.from(Cliente.class);
			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (cliente.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootCliente.get("id"), cliente.getId()));
			}
			if ((cliente.getEnderecoBairro() != null) && (cliente.getEnderecoBairro().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("enderecoBairro"),
						"%" + cliente.getEnderecoBairro() + "%"));
			}
			if ((cliente.getCargo() != null) && (cliente.getCargo().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("cargo"), "%" + cliente.getCargo() + "%"));
			}
			if ((cliente.getEnderecoCep() != null) && (cliente.getEnderecoCep().length() > 0)) {
				predicateList.add(
						criteriaBuilder.like(rootCliente.get("enderecoCep"), "%" + cliente.getEnderecoCep() + "%"));
			}
			if ((cliente.getEnderecoCidade() != null) && (cliente.getEnderecoCidade().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("enderecoCidade"),
						"%" + cliente.getEnderecoCidade() + "%"));
			}
			if ((cliente.getEnderecoComplemento() != null) && (cliente.getEnderecoComplemento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("enderecoComplemento"),
						"%" + cliente.getEnderecoComplemento() + "%"));
			}
			if ((cliente.getCpf() != null) && (cliente.getCpf().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("cpf"), "%" + cliente.getCpf() + "%"));
			}
			if ((cliente.getEmail() != null) && (cliente.getEmail().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("email"), "%" + cliente.getEmail() + "%"));
			}
			if ((cliente.getEnderecoEstado() != null) && (cliente.getEnderecoEstado().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("enderecoEstado"),
						"%" + cliente.getEnderecoEstado() + "%"));
			}
			if ((cliente.getEstadoCivil() != null) && (cliente.getEstadoCivil().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("enderecoEstadoCivil"),
						"%" + cliente.getEstadoCivil() + "%"));
			}
			if ((cliente.getFone1() != null) && (cliente.getFone1().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("fone1"), "%" + cliente.getFone1() + "%"));
			}
			if ((cliente.getFone2() != null) && (cliente.getFone2().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("fone2"), "%" + cliente.getFone2() + "%"));
			}
			if ((cliente.getEnderecoLogradouro() != null) && (cliente.getEnderecoLogradouro().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("enderecoLogradouro"),
						"%" + cliente.getEnderecoLogradouro() + "%"));
			}
			if ((cliente.getNome() != null) && (cliente.getNome().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("nome"), "%" + cliente.getNome() + "%"));
			}
			if ((cliente.getEnderecoPais() != null) && (cliente.getEnderecoPais().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("pais"), "%" + cliente.getEnderecoPais() + "%"));
			}
			if ((cliente.getCnpj() != null) && (cliente.getCnpj().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("cnpj"), "%" + cliente.getCnpj() + "%"));
			}
			if ((cliente.getRgNumero() != null) && (cliente.getRgNumero().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("rgNumero"), "%" + cliente.getRgNumero() + "%"));
			}
			if ((cliente.getRgOrgaoEmissor() != null) && (cliente.getRgOrgaoEmissor().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("rgOrgaoEmissor"),
						"%" + cliente.getRgOrgaoEmissor() + "%"));
			}
			if ((cliente.getSalario() != null) && (cliente.getSalario().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("salario"), "%" + cliente.getSalario() + "%"));
			}
			if ((cliente.getSexo() != null) && (cliente.getSexo().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootCliente.get("sexo"), "%" + cliente.getSexo() + "%"));
			}
			if (cliente.getEmpresa() != null) {
				predicateList.add(criteriaBuilder.equal(rootCliente.get("empresa"), cliente.getEmpresa()));
			}

			criteriaQuery.select(rootCliente).where(predicateList.toArray(new Predicate[] {}));
			clienteList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return clienteList;
	}

	@Override
	public void salvarRegistro(Cliente cliente) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(cliente);
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