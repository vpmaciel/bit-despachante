package erp.negocio.veiculo.documento;

import java.util.Collection;

public final class VeiculoDocumentoFac {

	private static final VeiculoDocumentoDao veiculoDocumentoDao = new VeiculoDocumentoImp();

	public static void deletarRegistro(VeiculoDocumento veiculoDocumento) {

		veiculoDocumentoDao.deletarRegistro(veiculoDocumento);
	}

	public static Collection<VeiculoDocumento> getRegistro() {

		return veiculoDocumentoDao.getRegistro();
	}

	public static VeiculoDocumento getRegistro(VeiculoDocumento veiculoDocumento) {

		return veiculoDocumentoDao.getRegistro(veiculoDocumento);
	}

	public static Collection<VeiculoDocumento> pesquisarRegistro(VeiculoDocumento veiculoDocumento) {

		return veiculoDocumentoDao.pesquisarRegistro(veiculoDocumento);
	}

	public static void salvarRegistro(VeiculoDocumento veiculoDocumento) {

		veiculoDocumentoDao.salvarRegistro(veiculoDocumento);
	}

	private VeiculoDocumentoFac() {
	}
}
