package erp.financeiro.cheque;

import java.util.Comparator;

public final class ChequeComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Cheque) o1).getId().compareTo(((Cheque) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Cheque) o1).getNome().compareToIgnoreCase(((Cheque) o2).getNome());
		}
	}

}
