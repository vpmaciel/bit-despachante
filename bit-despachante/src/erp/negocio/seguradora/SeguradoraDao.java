package erp.negocio.seguradora;

import java.util.Collection;

interface SeguradoraDao {

	void deletarRegistro(Seguradora seguradora);

	Collection<Seguradora> getRegistro();

	Seguradora getRegistro(Seguradora seguradora);

	Collection<Seguradora> pesquisarRegistro(Seguradora seguradora);

	void salvarRegistro(Seguradora seguradora);
}
