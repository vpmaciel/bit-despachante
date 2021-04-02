package erp.financeiro.contasreceber;

import java.util.Collection;

interface ContasReceberDao {

	void deletarRegistro(ContasReceber contasReceber);

	Collection<ContasReceber> getRegistro();

	ContasReceber getRegistro(ContasReceber contasReceber);

	Collection<ContasReceber> pesquisarRegistro(ContasReceber contasReceber);

	void salvarRegistro(ContasReceber contasReceber);
}
