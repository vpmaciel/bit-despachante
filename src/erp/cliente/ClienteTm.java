package erp.cliente;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class ClienteTm extends AbstractTableModel {

    public static final int ID = 0;
    public static int[] largura;
    private static boolean[] podeEditar;
    private static TabelaModelo tabelaModelo = new TabelaModelo();
    static {
	tabelaModelo.adicionarColuna("ID", 0, 150);
	tabelaModelo.adicionarColuna("NOME", 1, 400);
	tabelaModelo.adicionarColuna("CPF | CNPJ", 2, 200);

	largura = new int[tabelaModelo.getTotalColunas()];
	podeEditar = new boolean[tabelaModelo.getTotalColunas()];
	for (int i = 0; i < tabelaModelo.getTotalColunas(); i++) {
	    largura[i] = tabelaModelo.getLargura(i);
	    podeEditar[i] = false;
	}
    }
    private Cliente cliente;

    private List<Cliente> clienteList = new LinkedList<>();

    public ClienteTm() {

    }

    public ClienteTm(List<Cliente> lista) {
	clienteList.addAll(lista);
    }

    public Cliente getCliente(int linha) {
	if (clienteList.size() > 0) {
	    return clienteList.get(linha);
	}
	return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

	if (tabelaModelo.getNome(columnIndex).equals("ID")) {
	    return Long.class;
	}

	if (tabelaModelo.getNome(columnIndex).equals("NOME")
		|| tabelaModelo.getNome(columnIndex).equals("CPF | CNPJ")) {
	    return String.class;
	}

	return String.class;
    }

    @Override
    public int getColumnCount() {
	return largura.length;
    }

    @Override
    public String getColumnName(int column) {
	return tabelaModelo.getNome(column);
    }

    public List<Cliente> getContaList() {
	return clienteList;
    }

    @Override
    public int getRowCount() {
	return clienteList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Cliente cliente = clienteList.get(rowIndex);

	if (tabelaModelo.getNome(columnIndex).equals("ID")) {
	    return cliente.getId();
	}

	if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
	    if (cliente.getNome().length() > 50)
		return cliente.getNome().substring(0, 50);
	    else
		return cliente.getNome();
	}

	if (tabelaModelo.getNome(columnIndex).equals("CPF | CNPJ")) {
	    return cliente.getCpfCnpj();
	}

	return cliente;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	return podeEditar[columnIndex];
    }

    public void setContaList(List<Cliente> clienteList) {
	this.clienteList = clienteList;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	cliente = clienteList.get(rowIndex);

	if (tabelaModelo.getNome(columnIndex).equals("ID")) {
	    cliente.setId(Long.parseLong(aValue.toString()));
	}

	if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
	    cliente.setNome(aValue.toString());
	}

	if (tabelaModelo.getNome(columnIndex).equals("CPF | CNPJ")) {
	    cliente.setCpfCnpj(aValue.toString());
	}

	fireTableDataChanged();
    }
}