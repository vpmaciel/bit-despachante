package erp.negocio.produto;

import java.util.Collection;

public final class ProdutoFac {

	private static final ProdutoDao ProdutoDao = new ProdutoImp();

	public static void deletarRegistro(Produto produto) {

		ProdutoDao.deletarRegistro(produto);
	}

	public static Collection<Produto> getRegistro() {

		return ProdutoDao.getRegistro();
	}

	public static Produto getRegistro(Produto produto) {

		return ProdutoDao.getRegistro(produto);
	}

	public static Collection<Produto> pesquisarRegistro(Produto produto) {

		return ProdutoDao.pesquisarRegistro(produto);
	}

	public static void salvarProduto(Produto produto) {

		ProdutoDao.salvarRegistro(produto);
	}

	private ProdutoFac() {

	}
}
