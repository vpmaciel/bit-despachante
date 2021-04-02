package erp.negocio.servico;

import java.util.Collection;

interface ServicoDao {

	void deletarRegistro(Servico servico);

	Collection<Servico> getRegistro();

	Servico getRegistro(Servico servico);

	Collection<Servico> pesquisarRegistro(Servico servico);

	void salvarRegistro(Servico servico);
}
