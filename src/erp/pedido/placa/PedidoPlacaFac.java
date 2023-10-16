package erp.pedido.placa;

import java.util.Collection;

public final class PedidoPlacaFac {

	private static final PedidoPlacaDao marcaDao = new PedidoPlacaImp();

	public static void deletarRegistro(PedidoPlaca marca) {
		marcaDao.deletarRegistro(marca);
	}

	public static Collection<PedidoPlaca> getRegistro() {
		return marcaDao.getRegistro();
	}

	public static PedidoPlaca getRegistro(PedidoPlaca marca) {
		return marcaDao.getRegistro(marca);
	}

	public static boolean isRegistroValido(PedidoPlaca marca) {
		return marcaDao.isRegistroValido(marca);
	}

	public static Collection<PedidoPlaca> pesquisarRegistro(PedidoPlaca marca) {
		return marcaDao.pesquisarRegistro(marca);
	}

	public static void salvarRegistro(PedidoPlaca marca) {
		marcaDao.salvarRegistro(marca);
	}

	private PedidoPlacaFac() {
	}
}
