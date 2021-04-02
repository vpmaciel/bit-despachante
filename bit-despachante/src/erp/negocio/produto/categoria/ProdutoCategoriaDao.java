package erp.negocio.produto.categoria;

import java.util.Collection;

interface ProdutoCategoriaDao {

	void deletarRegistro(ProdutoCategoria servico);

	Collection<ProdutoCategoria> getRegistro();

	ProdutoCategoria getRegistro(ProdutoCategoria servico);

	Collection<ProdutoCategoria> pesquisarRegistro(ProdutoCategoria servico);

	void salvarRegistro(ProdutoCategoria servico);
}
