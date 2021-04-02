package erp.escritorio.produto.compra.lista;

import java.util.Collection;

public final class CompraProdutoFac {

	private static final CompraProdutoDao compraProdutoDao = new CompraProdutoImp();

	public static void deletarRegistro(CompraProduto compraProduto) {

		compraProdutoDao.deletarRegistro(compraProduto);
	}

	public static Collection<CompraProduto> getRegistro() {

		return compraProdutoDao.getRegistro();
	}

	public static CompraProduto getRegistro(CompraProduto compraProduto) {

		return compraProdutoDao.getRegistro(compraProduto);
	}

	public static Collection<CompraProduto> pesquisarRegistro(CompraProduto compraProduto) {

		return compraProdutoDao.pesquisarRegistro(compraProduto);
	}

	public static void salvarRegistro(CompraProduto compraProduto) {

		compraProdutoDao.salvarRegistro(compraProduto);
	}

	private CompraProdutoFac() {
	}
}
