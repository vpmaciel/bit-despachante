package erp.financeiro.vendas.produto;

import java.util.Collection;

interface VendaProdutoDao {

	void deletarRegistro(VendaProduto vendaProduto);

	Collection<VendaProduto> getRegistro();

	VendaProduto getRegistro(VendaProduto vendaProduto);

	Collection<VendaProduto> pesquisarRegistro(VendaProduto vendaProduto);

	void salvarRegistro(VendaProduto vendaProduto);
}
