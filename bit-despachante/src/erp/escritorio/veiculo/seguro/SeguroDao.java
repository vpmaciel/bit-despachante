package erp.escritorio.veiculo.seguro;

import java.util.Collection;

interface SeguroDao {

	void deletarRegistro(Seguro seguro);

	Collection<Seguro> getRegistro();

	Seguro getRegistro(Seguro seguro);

	Collection<Seguro> pesquisarRegistro(Seguro seguro);

	void salvarRegistro(Seguro seguro);
}
