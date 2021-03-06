package erp.negocio.cliente;

import java.util.Comparator;

public class ClienteComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Cliente) o1).getId().compareTo(((Cliente) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Cliente) o1).getNome().compareToIgnoreCase(((Cliente) o2).getNome());
		}
	}

}
