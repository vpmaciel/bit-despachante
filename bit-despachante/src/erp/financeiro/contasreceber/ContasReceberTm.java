package erp.financeiro.contasreceber;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class ContasReceberTm extends AbstractTableModel {

	public static final int ID = 0;
	public static int[] largura;
	private static boolean[] podeEditar;
	private static TabelaModelo tabelaModelo = new TabelaModelo();
	static {
		tabelaModelo.adicionarColuna("ID", 0, 100);
		tabelaModelo.adicionarColuna("NOME", 1, 500);

		largura = new int[tabelaModelo.getTotalColunas()];
		podeEditar = new boolean[tabelaModelo.getTotalColunas()];
		for (int i = 0; i < tabelaModelo.getTotalColunas(); i++) {
			largura[i] = tabelaModelo.getLargura(i);
			podeEditar[i] = false;
		}
	}
	private ContasReceber contasReceber;

	private List<ContasReceber> contasReceberList = new LinkedList<>();

	public ContasReceberTm() {

	}

	public ContasReceberTm(List<ContasReceber> lista) {
		contasReceberList.addAll(lista);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return Long.class;
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

	public ContasReceber getContasReceber(int linha) {
		if (contasReceberList.size() > 0) {
			return contasReceberList.get(linha);
		}
		return null;
	}

	public List<ContasReceber> getContasReceberList() {
		return contasReceberList;
	}

	@Override
	public int getRowCount() {
		return contasReceberList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ContasReceber contasReceber = contasReceberList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return contasReceber.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return contasReceber.getDescricao();
		}
		return contasReceber;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setContasReceberList(List<ContasReceber> contasReceber) {
		contasReceberList = contasReceber;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		contasReceber = contasReceberList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			contasReceber.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			contasReceber.setDescricao(aValue.toString());
		}

		fireTableDataChanged();
	}
}