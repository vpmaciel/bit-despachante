package erp.pedido.placa;

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

final class PedidoPlacaImp implements PedidoPlacaDao {

    @Override
    public void deletarRegistro(PedidoPlaca pedidoPlaca) {
	EntityManager entityManager = null;
	EntityTransaction entityTransaction = null;
	try {
	    entityManager = Jpa.getEntityManagerFactory().createEntityManager();
	    entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();
	    entityManager.remove(entityManager.find(PedidoPlaca.class, pedidoPlaca.getId()));
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
    public Collection<PedidoPlaca> getRegistro() {
	EntityManager entityManager = null;
	EntityTransaction entityTransaction = null;
	List<PedidoPlaca> pedidoPlacaList = null;

	try {
	    entityManager = Jpa.getEntityManagerFactory().createEntityManager();
	    entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();
	    Query query = entityManager.createQuery("select T from PedidoPlaca T order by T.data", PedidoPlaca.class);
	    pedidoPlacaList = query.getResultList();
	} catch (Exception exception) {
	    exception.printStackTrace();
	    throw exception;
	} finally {
	    if (entityManager.isOpen()) {
		entityManager.close();
	    }
	}
	return pedidoPlacaList;
    }

    @Override
    public PedidoPlaca getRegistro(PedidoPlaca marca) {
	EntityManager entityManager = null;
	EntityTransaction entityTransaction = null;
	try {
	    entityManager = Jpa.getEntityManagerFactory().createEntityManager();
	    entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();
	    marca = entityManager.find(PedidoPlaca.class, marca.getId());
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
    public Collection<PedidoPlaca> pesquisarRegistro(PedidoPlaca marca) {
	EntityManager entityManager = null;
	EntityTransaction entityTransaction = null;
	List<PedidoPlaca> marcaList = null;
	try {
	    entityManager = Jpa.getEntityManagerFactory().createEntityManager();
	    entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<PedidoPlaca> criteriaQuery = criteriaBuilder.createQuery(PedidoPlaca.class);
	    Root<PedidoPlaca> rootMarca = criteriaQuery.from(PedidoPlaca.class);
	    List<Predicate> predicateList = new ArrayList<>();

	    if ((marca.getCpfCnpjProprietario() != null) && (marca.getCpfCnpjProprietario().length() > 0)) {
		predicateList.add(
			criteriaBuilder.equal(rootMarca.get("cpfCnpjProprietario"), marca.getCpfCnpjProprietario()));
	    }

	    if ((marca.getPlaca() != null) && (marca.getPlaca().length() > 0)) {
		predicateList.add(criteriaBuilder.equal(rootMarca.get("placa"), marca.getPlaca()));
	    }

	    if ((marca.getRenavam() != null) && (marca.getRenavam().length() > 0)) {
		predicateList.add(criteriaBuilder.equal(rootMarca.get("renavam"), marca.getRenavam()));
	    }

	    if ((marca.getCorPlaca() != null) && (marca.getCorPlaca().length() > 0)) {
		predicateList.add(criteriaBuilder.equal(rootMarca.get("corPlaca"), marca.getCorPlaca()));
	    }

	    if ((marca.getTipoPlaca() != null) && (marca.getTipoPlaca().length() > 0)) {
		predicateList.add(criteriaBuilder.equal(rootMarca.get("tipoPlaca"), marca.getTipoPlaca()));
	    }
	    System.out.println(marca.getQuantidade());
	    if (marca.getQuantidade() != null) {
		predicateList.add(criteriaBuilder.equal(rootMarca.get("quantidade"), marca.getQuantidade()));
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
    public void salvarRegistro(PedidoPlaca marca) {
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