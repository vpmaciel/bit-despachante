package erp.financeiro.vendas.produto;

import java.util.Comparator;

public final class VendaProdutoComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((VendaProduto) o1).getId().compareTo(((VendaProduto) o2).getId());
		}
	}

}
