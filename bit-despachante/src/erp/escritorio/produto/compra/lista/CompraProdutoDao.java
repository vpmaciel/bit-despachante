package erp.escritorio.produto.compra.lista;

import java.util.Collection;

interface CompraProdutoDao {

	void deletarRegistro(CompraProduto compraProduto);

	Collection<CompraProduto> getRegistro();

	CompraProduto getRegistro(CompraProduto compraProduto);

	Collection<CompraProduto> pesquisarRegistro(CompraProduto compraProduto);

	void salvarRegistro(CompraProduto compraProduto);
}
