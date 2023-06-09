package erp.sistema.pessoa;

import java.util.Collection;

public interface PessoaDao {

	void deletarRegistro(Pessoa pessoa);

	Collection<Pessoa> getRegistro();

	Pessoa getRegistro(Pessoa pessoa);

	boolean isRegistroValido(Pessoa pessoa);

	Collection<Pessoa> pesquisarRegistro(Pessoa pessoa);

	void salvarRegistro(Pessoa pessoa);
}
