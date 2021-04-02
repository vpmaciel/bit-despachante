package erp.escritorio.veiculo.seguro;

import java.util.Comparator;

public final class SeguroComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Seguro) o1).getId().compareTo(((Seguro) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Seguro) o1).getNome().compareToIgnoreCase(((Seguro) o2).getNome());
		}
	}

}
