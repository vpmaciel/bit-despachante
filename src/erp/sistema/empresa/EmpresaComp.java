package erp.sistema.empresa;

import java.util.Comparator;

public final class EmpresaComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Empresa) o1).getId().compareTo(((Empresa) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Empresa) o1).getNome().compareToIgnoreCase(((Empresa) o2).getNome());
		}
	}

}
