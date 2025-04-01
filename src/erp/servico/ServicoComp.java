package erp.servico;

import java.util.Comparator;

public final class ServicoComp {

    public class Id implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
	    return ((Servico) o1).getDescricao().compareTo(((Servico) o2).getDescricao());
	}
    }
}
