package erp.negocio.produto;

import java.util.Comparator;

public final class ProdutoComp {

	public class Nome implements Comparator<Object> {
		@Override
		public int compare(Object o1, Object o2) {
			return ((Produto) o1).getNome().compareToIgnoreCase(((Produto) o2).getNome());
		}
	}
}
