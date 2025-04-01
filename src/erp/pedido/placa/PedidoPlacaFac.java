package erp.pedido.placa;

import java.util.Collection;

public final class PedidoPlacaFac {

    private static final PedidoPlacaDao pedidoPlacaDao = new PedidoPlacaImp();

    public static void deletarRegistro(PedidoPlaca marca) {
	pedidoPlacaDao.deletarRegistro(marca);
    }

    public static Collection<PedidoPlaca> getRegistro() {
	return pedidoPlacaDao.getRegistro();
    }

    public static PedidoPlaca getRegistro(PedidoPlaca marca) {
	return pedidoPlacaDao.getRegistro(marca);
    }

    public static Collection<PedidoPlaca> pesquisarRegistro(PedidoPlaca marca) {
	return pedidoPlacaDao.pesquisarRegistro(marca);
    }

    public static void salvarRegistro(PedidoPlaca marca) {
	pedidoPlacaDao.salvarRegistro(marca);
    }

    private PedidoPlacaFac() {
    }
}
