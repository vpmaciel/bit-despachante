package erp.utilitarios.agenda.evento.tipoevento;

import java.util.Comparator;

public class TipoEventoComp {

	public class Nome implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((TipoEvento) o1).getNome().compareToIgnoreCase(((TipoEvento) o2).getNome());
		}
	}
}
