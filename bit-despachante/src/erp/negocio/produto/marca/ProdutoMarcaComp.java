package erp.negocio.produto.marca;

import java.util.Comparator;

public final class ProdutoMarcaComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((ProdutoMarca) o1).getId().compareTo(((ProdutoMarca) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((ProdutoMarca) o1).getNome().compareToIgnoreCase(((ProdutoMarca) o2).getNome());
		}
	}

}
