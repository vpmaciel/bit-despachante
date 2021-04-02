package erp.escritorio.veiculo.pedido.placa;

import java.util.Collection;

interface PedidoPlacaDao {

	void deletarRegistro(PedidoPlaca pedidoPlaca);

	Collection<PedidoPlaca> getRegistro();

	PedidoPlaca getRegistro(PedidoPlaca pedidoPlaca);

	Collection<PedidoPlaca> pesquisarRegistro(PedidoPlaca pedidoPlaca);

	void salvarRegistro(PedidoPlaca pedidoPlaca);
}
