package erp.cliente;

import java.util.Comparator;

public final class ClienteComp {

    public class Id implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
	    return ((Cliente) o1).getCpfCnpj().compareTo(((Cliente) o2).getCpfCnpj());
	}
    }
}
