package erp.negocio.produto;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class ProdutoTm extends AbstractTableModel {

	public static final int ID = 0;
	public static int[] largura;
	private static boolean[] podeEditar;
	private static TabelaModelo tabelaModelo = new TabelaModelo();
	static {
		tabelaModelo.adicionarColuna("ID", 0, 100);
		tabelaModelo.adicionarColuna("NOME", 1, 500);
		tabelaModelo.adicionarColuna("PREÇO", 2, 100);

		largura = new int[tabelaModelo.getTotalColunas()];
		podeEditar = new boolean[tabelaModelo.getTotalColunas()];
		for (int i = 0; i < tabelaModelo.getTotalColunas(); i++) {
			largura[i] = tabelaModelo.getLargura(i);
			podeEditar[i] = false;
		}
	}
	private Produto produto;

	private List<Produto> produtoList = new LinkedList<>();

	public ProdutoTm() {

	}

	public ProdutoTm(List<Produto> lista) {
		produtoList.addAll(lista);
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

	public Produto getProduto(int linha) {
		if (produtoList.size() > 0) {
			return produtoList.get(linha);
		}
		return null;
	}

	public List<Produto> getProdutoList() {
		return produtoList;
	}

	@Override
	public int getRowCount() {
		return produtoList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Produto produto = produtoList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return produto.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return produto.getNome();
		}
		if (tabelaModelo.getNome(columnIndex).equals("PREÇO")) {
			return produto.getPreco();
		}
		return produto;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setProdutoList(List<Produto> produto) {
		produtoList = produto;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		produto = produtoList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			produto.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			produto.setNome(aValue.toString());
		}
		if (tabelaModelo.getNome(columnIndex).equals("CÓDIGO")) {
			produto.setPreco(Double.parseDouble(aValue.toString()));
		}

		fireTableDataChanged();
	}
}