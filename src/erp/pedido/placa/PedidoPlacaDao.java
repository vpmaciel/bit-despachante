package erp.pedido.placa;

import java.util.Collection;

public interface PedidoPlacaDao {

	void deletarRegistro(PedidoPlaca marca);

	Collection<PedidoPlaca> getRegistro();

	PedidoPlaca getRegistro(PedidoPlaca marca);

	Collection<PedidoPlaca> pesquisarRegistro(PedidoPlaca marca);

	void salvarRegistro(PedidoPlaca marca);
}
