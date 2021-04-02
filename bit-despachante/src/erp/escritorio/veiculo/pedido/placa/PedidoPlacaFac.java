package erp.escritorio.veiculo.pedido.placa;

import java.util.Collection;

public final class PedidoPlacaFac {

	private static final PedidoPlacaDao pedidoPlacaDao = new PedidoPlacaImp();

	public static void deletarRegistro(PedidoPlaca pedidoPlaca) {

		pedidoPlacaDao.deletarRegistro(pedidoPlaca);
	}

	public static Collection<PedidoPlaca> getRegistro() {

		return pedidoPlacaDao.getRegistro();
	}

	public static PedidoPlaca getRegistro(PedidoPlaca pedidoPlaca) {

		return pedidoPlacaDao.getRegistro(pedidoPlaca);
	}

	public static Collection<PedidoPlaca> pesquisarRegistro(PedidoPlaca pedidoPlaca) {

		return pedidoPlacaDao.pesquisarRegistro(pedidoPlaca);
	}

	public static void salvarRegistro(PedidoPlaca pedidoPlaca) {

		pedidoPlacaDao.salvarRegistro(pedidoPlaca);
	}

	private PedidoPlacaFac() {
	}
}
