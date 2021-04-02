package erp.escritorio.veiculo.pedido.placa;

import java.util.Comparator;

public final class PedidoPlacaComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((PedidoPlaca) o1).getId().compareTo(((PedidoPlaca) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((PedidoPlaca) o1).getNome().compareToIgnoreCase(((PedidoPlaca) o2).getNome());
		}
	}

}
