package erp.financeiro.cheque;

import java.util.Collection;

interface ChequeDao {

	void deletarRegistro(Cheque cheque);

	Collection<Cheque> getRegistro();

	Cheque getRegistro(Cheque cheque);

	Collection<Cheque> pesquisarRegistro(Cheque cheque);

	void salvarRegistro(Cheque cheque);
}
