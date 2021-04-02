package erp.financeiro.vendas.servico;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class VendaServicoTm extends AbstractTableModel {

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
	private VendaServico vendaServico;

	private List<VendaServico> vendaServicoList = new LinkedList<>();

	public VendaServicoTm() {

	}

	public VendaServicoTm(List<VendaServico> lista) {
		vendaServicoList.addAll(lista);
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
		return vendaServicoList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		VendaServico vendaServico = vendaServicoList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return vendaServico.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			return vendaServico.getUsuario().getNome();
		}
		return vendaServico;
	}

	public VendaServico getVendaServico(int linha) {
		if (vendaServicoList.size() > 0) {
			return vendaServicoList.get(linha);
		}
		return null;
	}

	public List<VendaServico> getVendaServicoList() {
		return vendaServicoList;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		vendaServico = vendaServicoList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			vendaServico.setId(Long.parseLong(aValue.toString()));
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME")) {
			// vendaServico.setNome(aValue.toString());
		}

		fireTableDataChanged();
	}

	public void setVendaServicoList(List<VendaServico> vendaServico) {
		vendaServicoList = vendaServico;
	}
}