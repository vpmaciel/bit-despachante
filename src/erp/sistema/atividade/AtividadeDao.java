package erp.sistema.atividade;

import java.util.Collection;

public interface AtividadeDao {

	void deletarRegistro(Atividade atividade);

	Collection<Atividade> getRegistro();

	Atividade getRegistro(Atividade atividade);

	boolean isRegistroValido(Atividade atividade);

	Collection<Atividade> pesquisarRegistro(Atividade atividade);

	void salvarRegistro(Atividade atividade);
}
