package erp.negocio.produto.marca;

import java.util.Collection;

interface ProdutoMarcaDao {

	void deletarRegistro(ProdutoMarca produtoMarca);

	Collection<ProdutoMarca> getRegistro();

	ProdutoMarca getRegistro(ProdutoMarca produtoMarca);

	Collection<ProdutoMarca> pesquisarRegistro(ProdutoMarca produtoMarca);

	void salvarRegistro(ProdutoMarca produtoMarca);
}
