package erp.negocio.produto.categoria;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class ProdutoCategoriaTm extends AbstractTableModel {

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
	private ProdutoCategoria servico;

	private List<ProdutoCategoria> servicoList = new LinkedList<>();

	public ProdutoCategoriaTm() {

	}

	public ProdutoCategoriaTm(List<ProdutoCategoria> lista) {
		servicoList.addAll(lista);
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

	public ProdutoCategoria getProdutoCategoria(int linha) {
		if (servicoList.size() > 0) {
			return servicoList.get(linha);
		}
		return null;
	}

	public List<ProdutoCategoria> getProdutoCategoriaList() {
		return servicoList;
	}

	@Override
	public int getRowCount() {
		return servicoList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ProdutoCategoria servico = servicoList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return servico.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return servico.getNome();
		}
		return servico;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setProdutoCategoriaList(List<ProdutoCategoria> servico) {
		servicoList = servico;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		servico = servicoList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			servico.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			servico.setNome(aValue.toString());
		}

		fireTableDataChanged();
	}
}