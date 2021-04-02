package erp.sistema.dados;

import java.util.Comparator;

public final class DadosComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Dados) o1).getId().compareTo(((Dados) o2).getId());
		}
	}

}
