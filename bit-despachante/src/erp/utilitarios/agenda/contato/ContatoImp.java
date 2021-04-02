package erp.utilitarios.agenda.contato;

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

final class ContatoImp implements ContatoDao {

	@Override
	public void deletarRegistro(Contato contato) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();
			entityManager.remove(entityManager.find(Contato.class, contato.getId()));
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
	public Collection<Contato> getRegistro() {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Contato> contatoList = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("select T from Contato T order by T.nome", Contato.class);
			contatoList = query.getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return contatoList;
	}

	@Override
	public Contato getRegistro(Contato contato) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			contato = entityManager.find(Contato.class, contato.getId());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return contato;
	}

	@Override
	public Collection<Contato> pesquisarRegistro(Contato contato) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		List<Contato> contatoList = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Contato> criteriaQuery = criteriaBuilder.createQuery(Contato.class);
			Root<Contato> rootContato = criteriaQuery.from(Contato.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			if (contato.getId() != null) {
				predicateList.add(criteriaBuilder.equal(rootContato.get("id"), contato.getId()));
			}
			if ((contato.getEnderecoBairro() != null) && (contato.getEnderecoBairro().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootContato.get("enderecoBairro"),
						"%" + contato.getEnderecoBairro() + "%"));
			}
			if ((contato.getEnderecoCep() != null) && (contato.getEnderecoCep().length() > 0)) {
				predicateList.add(
						criteriaBuilder.like(rootContato.get("enderecoCep"), "%" + contato.getEnderecoCep() + "%"));
			}
			if ((contato.getEnderecoCidade() != null) && (contato.getEnderecoCidade().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootContato.get("enderecoCidade"),
						"%" + contato.getEnderecoCidade() + "%"));
			}
			if ((contato.getCnpj() != null) && (contato.getCnpj().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootContato.get("cnpj"), "%" + contato.getCnpj() + "%"));
			}
			if ((contato.getEnderecoComplemento() != null) && (contato.getEnderecoComplemento().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootContato.get("enderecoComplemento"),
						"%" + contato.getEnderecoComplemento() + "%"));
			}
			if ((contato.getCpf() != null) && (contato.getCpf().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootContato.get("cpf"), "%" + contato.getCpf() + "%"));
			}
			if ((contato.getEmail() != null) && (contato.getEmail().length() > 0)) {
				predicateList
						.add(criteriaBuilder.like(rootContato.get("enderecoEmail"), "%" + contato.getEmail() + "%"));
			}
			if ((contato.getEmpresa() != null) && (contato.getEmpresa().getId() != null)) {
				predicateList.add(criteriaBuilder.equal(rootContato.get("enderecoEmpresa"), contato.getEmpresa()));
			}
			if ((contato.getEnderecoEstado() != null) && (contato.getEnderecoEstado().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootContato.get("enderecoEstado"),
						"%" + contato.getEnderecoEstado() + "%"));
			}
			if ((contato.getFax() != null) && (contato.getFax().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootContato.get("fax"), "%" + contato.getFax() + "%"));
			}
			if ((contato.getFone1() != null) && (contato.getFone1().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootContato.get("fone1"), "%" + contato.getFone1() + "%"));
			}
			if ((contato.getFone2() != null) && (contato.getFone2().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootContato.get("fone2"), "%" + contato.getFone2() + "%"));
			}
			if ((contato.getEnderecoLogradouro() != null) && (contato.getEnderecoLogradouro().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootContato.get("enderecoLogradouro"),
						"%" + contato.getEnderecoLogradouro() + "%"));
			}
			if ((contato.getNome() != null) && (contato.getNome().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootContato.get("nome"), "%" + contato.getNome() + "%"));
			}
			if ((contato.getEnderecoPais() != null) && (contato.getEnderecoPais().length() > 0)) {
				predicateList.add(
						criteriaBuilder.like(rootContato.get("enderecoPais"), "%" + contato.getEnderecoPais() + "%"));
			}
			if ((contato.getSexo() != null) && (contato.getSexo().length() > 0)) {
				predicateList.add(criteriaBuilder.like(rootContato.get("sexo"), "%" + contato.getSexo() + "%"));
			}

			criteriaQuery.select(rootContato).where(predicateList.toArray(new Predicate[] {}));

			contatoList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		return contatoList;
	}

	@Override
	public void salvarRegistro(Contato contato) {
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		try {
			entityManager = Jpa.getEntityManagerFactory().createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(contato);
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