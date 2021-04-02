package erp.financeiro.contaspagar;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class ContasPagarTm extends AbstractTableModel {

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
	private ContasPagar contasPagar;

	private List<ContasPagar> contasPagarList = new LinkedList<>();

	public ContasPagarTm() {

	}

	public ContasPagarTm(List<ContasPagar> lista) {
		contasPagarList.addAll(lista);
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

	public ContasPagar getContasPagar(int linha) {
		if (contasPagarList.size() > 0) {
			return contasPagarList.get(linha);
		}
		return null;
	}

	public List<ContasPagar> getContasPagarList() {
		return contasPagarList;
	}

	@Override
	public int getRowCount() {
		return contasPagarList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ContasPagar contasPagar = contasPagarList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return contasPagar.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return contasPagar.getDescricao();
		}
		return contasPagar;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setContasPagarList(List<ContasPagar> contasPagar) {
		contasPagarList = contasPagar;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		contasPagar = contasPagarList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			contasPagar.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			contasPagar.setDescricao(aValue.toString());
		}

		fireTableDataChanged();
	}
}