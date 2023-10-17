package erp.veiculo;

import java.util.Collection;

public interface VeiculoDao {

	void deletarRegistro(Veiculo marca);

	Collection<Veiculo> getRegistro();

	Veiculo getRegistro(Veiculo marca);

	Collection<Veiculo> pesquisarRegistro(Veiculo marca);

	void salvarRegistro(Veiculo marca);
}
