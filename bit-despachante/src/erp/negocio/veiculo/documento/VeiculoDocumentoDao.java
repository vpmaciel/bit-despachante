package erp.negocio.veiculo.documento;

import java.util.Collection;

interface VeiculoDocumentoDao {

	void deletarRegistro(VeiculoDocumento veiculoDocumento);

	Collection<VeiculoDocumento> getRegistro();

	VeiculoDocumento getRegistro(VeiculoDocumento veiculoDocumento);

	Collection<VeiculoDocumento> pesquisarRegistro(VeiculoDocumento veiculoDocumento);

	void salvarRegistro(VeiculoDocumento veiculoDocumento);
}
