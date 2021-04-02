package erp.financeiro.contasreceber;

import java.util.Comparator;

public final class ContasReceberComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((ContasReceber) o1).getId().compareTo(((ContasReceber) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((ContasReceber) o1).getDescricao().compareToIgnoreCase(((ContasReceber) o2).getDescricao());
		}
	}

}
