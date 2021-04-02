package erp.sistema.dados;

import java.util.Collection;

public interface DadosDao {

	void deletarRegistro(Dados contador);

	Collection<Dados> getRegistro();

	Dados getRegistro(Dados contador);

	Collection<Dados> pesquisarRegistro(Dados contador);

	void salvarRegistro(Dados contador);
}
