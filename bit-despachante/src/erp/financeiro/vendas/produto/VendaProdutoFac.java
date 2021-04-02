package erp.financeiro.vendas.produto;

import java.util.Collection;

public final class VendaProdutoFac {

	private static final VendaProdutoDao vendaProdutoDao = new VendaProdutoImp();

	public static void deletarRegistro(VendaProduto vendaProduto) {

		vendaProdutoDao.deletarRegistro(vendaProduto);
	}

	public static Collection<VendaProduto> getRegistro() {

		return vendaProdutoDao.getRegistro();
	}

	public static VendaProduto getRegistro(VendaProduto vendaProduto) {

		return vendaProdutoDao.getRegistro(vendaProduto);
	}

	public static Collection<VendaProduto> pesquisarRegistro(VendaProduto vendaProduto) {

		return vendaProdutoDao.pesquisarRegistro(vendaProduto);
	}

	public static void salvarRegistro(VendaProduto vendaProduto) {

		vendaProdutoDao.salvarRegistro(vendaProduto);
	}

	private VendaProdutoFac() {
	}
}
