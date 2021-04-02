package erp.negocio.produto;

import java.util.Collection;

interface ProdutoDao {

	void deletarRegistro(Produto produto);

	Collection<Produto> getRegistro();

	Produto getRegistro(Produto produto);

	Collection<Produto> pesquisarRegistro(Produto produto);

	void salvarRegistro(Produto produto);
}
