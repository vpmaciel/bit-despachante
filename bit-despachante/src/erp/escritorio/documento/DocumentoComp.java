package erp.escritorio.documento;

import java.util.Comparator;

public class DocumentoComp {

	public class Id implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Documento) o1).getId().compareTo(((Documento) o2).getId());
		}
	}
}
