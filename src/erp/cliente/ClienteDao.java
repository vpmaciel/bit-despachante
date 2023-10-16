package erp.cliente;

import java.util.Collection;

public interface ClienteDao {

	void deletarRegistro(Cliente marca);

	Collection<Cliente> getRegistro();

	Cliente getRegistro(Cliente marca);

	boolean isRegistroValido(Cliente marca);

	Collection<Cliente> pesquisarRegistro(Cliente marca);

	void salvarRegistro(Cliente marca);
}
