package erp.negocio.servico;

import java.util.Comparator;

public final class ServicoComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Servico) o1).getId().compareTo(((Servico) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Servico) o1).getNome().compareToIgnoreCase(((Servico) o2).getNome());
		}
	}

}
