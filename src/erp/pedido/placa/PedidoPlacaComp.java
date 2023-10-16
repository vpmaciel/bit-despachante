package erp.pedido.placa;

import java.util.Comparator;

public final class PedidoPlacaComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((PedidoPlaca) o1).getDescricao().compareTo(((PedidoPlaca) o2).getDescricao());
		}
	}
}
