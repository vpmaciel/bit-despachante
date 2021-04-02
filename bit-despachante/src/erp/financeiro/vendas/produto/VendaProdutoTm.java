package erp.financeiro.vendas.produto;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class VendaProdutoTm extends AbstractTableModel {

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
	private VendaProduto vendaProduto;

	private List<VendaProduto> vendaProdutoList = new LinkedList<>();

	public VendaProdutoTm() {

	}

	public VendaProdutoTm(List<VendaProduto> lista) {
		vendaProdutoList.addAll(lista);
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

	@Override
	public int getRowCount() {
		return vendaProdutoList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		VendaProduto vendaProduto = vendaProdutoList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return vendaProduto.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return vendaProduto.getProduto();
		}
		return vendaProduto;
	}

	public VendaProduto getVendaProduto(int linha) {
		if (vendaProdutoList.size() > 0) {
			return vendaProdutoList.get(linha);
		}
		return null;
	}

	public List<VendaProduto> getVendaProdutoList() {
		return vendaProdutoList;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		vendaProduto = vendaProdutoList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			vendaProduto.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			// vendaProduto.setProduto(aValue.toString());
		}

		fireTableDataChanged();
	}

	public void setVendaProdutoList(List<VendaProduto> vendaProduto) {
		vendaProdutoList = vendaProduto;
	}
}