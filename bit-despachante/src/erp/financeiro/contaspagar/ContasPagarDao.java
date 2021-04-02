package erp.financeiro.contaspagar;

import java.util.Collection;

interface ContasPagarDao {

	void deletarRegistro(ContasPagar contasPagar);

	Collection<ContasPagar> getRegistro();

	ContasPagar getRegistro(ContasPagar contasPagar);

	Collection<ContasPagar> pesquisarRegistro(ContasPagar contasPagar);

	void salvarRegistro(ContasPagar contasPagar);
}
