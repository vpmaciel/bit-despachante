package erp.negocio.produto.unidade;

import java.util.Comparator;

public final class ProdutoUnidadeComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((ProdutoUnidade) o1).getId().compareTo(((ProdutoUnidade) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((ProdutoUnidade) o1).getNome().compareToIgnoreCase(((ProdutoUnidade) o2).getNome());
		}
	}

}
