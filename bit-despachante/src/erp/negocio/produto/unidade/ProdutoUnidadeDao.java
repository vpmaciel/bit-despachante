package erp.negocio.produto.unidade;

import java.util.Collection;

interface ProdutoUnidadeDao {

	void deletarRegistro(ProdutoUnidade produtoUnidade);

	Collection<ProdutoUnidade> getRegistro();

	ProdutoUnidade getRegistro(ProdutoUnidade produtoUnidade);

	Collection<ProdutoUnidade> pesquisarRegistro(ProdutoUnidade produtoUnidade);

	void salvarRegistro(ProdutoUnidade produtoUnidade);
}
