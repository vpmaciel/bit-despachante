package erp.escritorio.documento;

import java.util.Collection;

public final class DocumentoFac {

	private static final DocumentoDao veiculoDocumentoDao = new DocumentoImp();

	public static void deletarRegistro(Documento veiculoDocumento) {

		veiculoDocumentoDao.deletarRegistro(veiculoDocumento);
	}

	public static Collection<Documento> getRegistro() {

		return veiculoDocumentoDao.getRegistro();
	}

	public static Documento getRegistro(Documento veiculoDocumento) {

		return veiculoDocumentoDao.getRegistro(veiculoDocumento);
	}

	public static Collection<Documento> pesquisarRegistro(Documento veiculoDocumento) {

		return veiculoDocumentoDao.pesquisarRegistro(veiculoDocumento);
	}

	public static void salvarRegistro(Documento veiculoDocumento) {

		veiculoDocumentoDao.salvarRegistro(veiculoDocumento);
	}

	private DocumentoFac() {
	}
}
