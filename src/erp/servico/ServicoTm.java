package erp.servico;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.Data;
import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class ServicoTm extends AbstractTableModel {

    public static final int ID = 0;
    public static int[] largura;
    private static boolean[] podeEditar;
    private static TabelaModelo tabelaModelo = new TabelaModelo();
    static {
	tabelaModelo.adicionarColuna("ID", 0, 50);
	tabelaModelo.adicionarColuna("DATA", 1, 100);
	tabelaModelo.adicionarColuna("PLACA DO VEÍCULO", 2, 200);
	tabelaModelo.adicionarColuna("DESCRIÇÃO DO SERVIÇO", 3, 500);
	tabelaModelo.adicionarColuna("VALOR DO SERVIÇO", 4, 200);

	largura = new int[tabelaModelo.getTotalColunas()];
	podeEditar = new boolean[tabelaModelo.getTotalColunas()];
	for (int i = 0; i < tabelaModelo.getTotalColunas(); i++) {
	    largura[i] = tabelaModelo.getLargura(i);
	    podeEditar[i] = false;
	}
    }
    private Servico marca;

    private List<Servico> marcaList = new LinkedList<>();

    public ServicoTm() {

    }

    public ServicoTm(List<Servico> lista) {
	marcaList.addAll(lista);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

	if (tabelaModelo.getNome(columnIndex).equals("ID")) {
	    return Long.class;
	}

	if (tabelaModelo.getNome(columnIndex).equals("DATA")) {
	    return Date.class;
	}

	if (tabelaModelo.getNome(columnIndex).equals("PLACA DO VEÍCULO")) {
	    return String.class;
	}

	if (tabelaModelo.getNome(columnIndex).equals("DESCRIÇÃO DO SERVIÇO")) {
	    return String.class;
	}

	if (tabelaModelo.getNome(columnIndex).equals("VALOR DO SERVIÇO")) {
	    return Float.class;
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

    public List<Servico> getContaList() {
	return marcaList;
    }

    public Servico getMarca(int linha) {
	if (marcaList.size() > 0) {
	    return marcaList.get(linha);
	}
	return null;
    }

    @Override
    public int getRowCount() {
	return marcaList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Servico marca = marcaList.get(rowIndex);

	if (tabelaModelo.getNome(columnIndex).equals("ID")) {
	    return marca.getId();
	}

	if (tabelaModelo.getNome(columnIndex).equals("DATA")) {
	    return marca.getData();
	}

	if (tabelaModelo.getNome(columnIndex).equals("PLACA DO VEÍCULO")) {
	    return marca.getPlaca();
	}

	if (tabelaModelo.getNome(columnIndex).equals("DESCRIÇÃO DO SERVIÇO")) {
	    return marca.getDescricao();
	}

	if (tabelaModelo.getNome(columnIndex).equals("VALOR DO SERVIÇO")) {
	    return marca.getValor();
	}

	return marca;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	return podeEditar[columnIndex];
    }

    public void setContaList(List<Servico> marca) {
	marcaList = marca;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	marca = marcaList.get(rowIndex);

	if (tabelaModelo.getNome(columnIndex).equals("ID")) {
	    marca.setId(Long.parseLong(aValue.toString()));
	}

	if (tabelaModelo.getNome(columnIndex).equals("DATA")) {
	    marca.setData(Data.retornaData(aValue.toString()));
	}

	if (tabelaModelo.getNome(columnIndex).equals("PLACA DO VEÍCULO")) {
	    marca.setPlaca(aValue.toString());
	}

	if (tabelaModelo.getNome(columnIndex).equals("DESCRIÇÃO DO SERVIÇO")) {
	    marca.setDescricao(aValue.toString());
	}

	if (tabelaModelo.getNome(columnIndex).equals("VALOR DO SERVIÇO")) {
	    marca.setValor(Float.parseFloat(aValue.toString()));
	}

	fireTableDataChanged();
    }
}