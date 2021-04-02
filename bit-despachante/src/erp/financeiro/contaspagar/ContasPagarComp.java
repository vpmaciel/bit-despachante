package erp.financeiro.contaspagar;

import java.util.Comparator;

public final class ContasPagarComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((ContasPagar) o1).getId().compareTo(((ContasPagar) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((ContasPagar) o1).getDescricao().compareToIgnoreCase(((ContasPagar) o2).getDescricao());
		}
	}

}
