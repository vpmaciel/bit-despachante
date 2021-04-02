package erp.negocio.veiculo;

import java.util.Collection;

interface VeiculoDao {

	void deletarRegistro(Veiculo veiculo);

	Collection<Veiculo> getRegistro();

	Veiculo getRegistro(Veiculo veiculo);

	Collection<Veiculo> pesquisarRegistro(Veiculo veiculo);

	void salvarRegistro(Veiculo veiculo);
}
