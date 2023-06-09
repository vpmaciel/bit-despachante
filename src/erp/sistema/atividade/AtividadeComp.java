package erp.sistema.atividade;

import java.util.Comparator;

public final class AtividadeComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Atividade) o1).getId().compareTo(((Atividade) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Atividade) o1).getNome().compareToIgnoreCase(((Atividade) o2).getNome());
		}
	}

}
