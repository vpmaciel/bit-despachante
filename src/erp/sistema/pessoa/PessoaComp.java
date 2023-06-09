package erp.sistema.pessoa;

import java.util.Comparator;

public final class PessoaComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Pessoa) o1).getId().compareTo(((Pessoa) o2).getId());
		}
	}

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Pessoa) o1).getNome().compareToIgnoreCase(((Pessoa) o2).getNome());
		}
	}

}
