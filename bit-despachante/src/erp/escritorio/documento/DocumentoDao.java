package erp.escritorio.documento;

import java.util.Collection;

interface DocumentoDao {

	void deletarRegistro(Documento veiculoDocumento);

	Collection<Documento> getRegistro();

	Documento getRegistro(Documento veiculoDocumento);

	Collection<Documento> pesquisarRegistro(Documento veiculoDocumento);

	void salvarRegistro(Documento veiculoDocumento);
}
