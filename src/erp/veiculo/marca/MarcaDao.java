package erp.veiculo.marca;

import java.util.Collection;

public interface MarcaDao {

	void deletarRegistro(Marca marca);

	Collection<Marca> getRegistro();

	Marca getRegistro(Marca marca);

	boolean isRegistroValido(Marca marca);

	Collection<Marca> pesquisarRegistro(Marca marca);

	void salvarRegistro(Marca marca);
}
