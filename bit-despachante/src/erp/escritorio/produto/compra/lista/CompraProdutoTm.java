package erp.escritorio.produto.compra.lista;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class CompraProdutoTm extends AbstractTableModel {

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
	private CompraProduto compraProduto;

	private List<CompraProduto> compraProdutoList = new LinkedList<>();

	public CompraProdutoTm() {

	}

	public CompraProdutoTm(List<CompraProduto> lista) {
		compraProdutoList.addAll(lista);
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

	public CompraProduto getCompraProduto(int linha) {
		if (compraProdutoList.size() > 0) {
			return compraProdutoList.get(linha);
		}
		return null;
	}

	public List<CompraProduto> getCompraProdutoList() {
		return compraProdutoList;
	}

	@Override
	public int getRowCount() {
		return compraProdutoList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		CompraProduto compraProduto = compraProdutoList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return compraProduto.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return compraProduto.getNome();
		}
		return compraProduto;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setCompraProdutoList(List<CompraProduto> compraProduto) {
		compraProdutoList = compraProduto;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		compraProduto = compraProdutoList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			compraProduto.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			compraProduto.setNome(aValue.toString());
		}

		fireTableDataChanged();
	}
}