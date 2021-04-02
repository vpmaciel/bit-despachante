package erp.financeiro.caixa;

import java.util.Collection;

interface CaixaDao {

	void deletarRegistro(Caixa caixa);

	Collection<Caixa> getRegistro();

	Caixa getRegistro(Caixa caixa);

	Collection<Caixa> pesquisarRegistro(Caixa caixa);

	void salvarRegistro(Caixa caixa);
}
