package erp.negocio.seguradora;

import java.util.Comparator;

public final class SeguradoraComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Seguradora) o1).getId().compareTo(((Seguradora) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Seguradora) o1).getNome().compareToIgnoreCase(((Seguradora) o2).getNome());
		}
	}

}
