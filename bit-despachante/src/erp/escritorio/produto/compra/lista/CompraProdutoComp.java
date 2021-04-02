package erp.escritorio.produto.compra.lista;

import java.util.Comparator;

public final class CompraProdutoComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((CompraProduto) o1).getId().compareTo(((CompraProduto) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((CompraProduto) o1).getNome().compareToIgnoreCase(((CompraProduto) o2).getNome());
		}
	}

}
