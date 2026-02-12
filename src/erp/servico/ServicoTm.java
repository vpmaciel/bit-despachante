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
	tabelaModelo.adicionarColuna("ID", 0, 250);
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
    private Servico servico;

    private List<Servico> servicoList = new LinkedList<>();

    public ServicoTm() {

    }

    public ServicoTm(List<Servico> lista) {
	servicoList.addAll(lista);
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
	return servicoList;
    }

    public Servico getMarca(int linha) {
	if (servicoList.size() > 0) {
	    return servicoList.get(linha);
	}
	return null;
    }

    @Override
    public int getRowCount() {
	return servicoList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Servico servico = servicoList.get(rowIndex);

	if (tabelaModelo.getNome(columnIndex).equals("ID")) {
	    return servico.getId();
	}

	if (tabelaModelo.getNome(columnIndex).equals("DATA")) {
	    return servico.getDataFormatada();
	}

	if (tabelaModelo.getNome(columnIndex).equals("PLACA DO VEÍCULO")) {
	    return servico.getPlaca();
	}

	if (tabelaModelo.getNome(columnIndex).equals("DESCRIÇÃO DO SERVIÇO")) {
	    return servico.getDescricao();
	}

	if (tabelaModelo.getNome(columnIndex).equals("VALOR DO SERVIÇO")) {
	    return servico.getValorFormatado();
	}

	return servico;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	return podeEditar[columnIndex];
    }

    public void setContaList(List<Servico> marca) {
	servicoList = marca;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	servico = servicoList.get(rowIndex);

	if (tabelaModelo.getNome(columnIndex).equals("ID")) {
	    servico.setId(Long.parseLong(aValue.toString()));
	}

	if (tabelaModelo.getNome(columnIndex).equals("DATA")) {
	    servico.setData(Data.retornaData(aValue.toString()));
	}

	if (tabelaModelo.getNome(columnIndex).equals("PLACA DO VEÍCULO")) {
	    servico.setPlaca(aValue.toString());
	}

	if (tabelaModelo.getNome(columnIndex).equals("DESCRIÇÃO DO SERVIÇO")) {
	    servico.setDescricao(aValue.toString());
	}

	if (tabelaModelo.getNome(columnIndex).equals("VALOR DO SERVIÇO")) {
	    servico.setValor(Float.parseFloat(aValue.toString()));
	}

	fireTableDataChanged();
    }
}