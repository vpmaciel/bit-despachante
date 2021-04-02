package erp.negocio.veiculo.documento;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;
import erp.negocio.veiculo.Veiculo;
import erp.negocio.veiculo.VeiculoFac;

@SuppressWarnings("serial")
public class VeiculoDocumentoTm extends AbstractTableModel {

	public static final int ID = 0;
	public static int[] largura;
	private static boolean[] podeEditar;
	private static TabelaModelo tabelaModelo = new TabelaModelo();
	static {
		tabelaModelo.adicionarColuna("ID", 0, 100);
		tabelaModelo.adicionarColuna("SITUAÇÃO", 1, 200);
		tabelaModelo.adicionarColuna("LOCAL", 2, 200);
		tabelaModelo.adicionarColuna("PLACA", 3, 100);
		tabelaModelo.adicionarColuna("MARCA", 4, 300);
		tabelaModelo.adicionarColuna("MODELO", 5, 300);
		tabelaModelo.adicionarColuna("NOME DO PROPRIETÁRIO", 6, 300);
		tabelaModelo.adicionarColuna("DATA DE RECEBIMENTO DO DOCUMENTO", 7, 300);
		tabelaModelo.adicionarColuna("DATA DE DEVOLUÇÃO DO DOCUMENTO", 8, 300);

		largura = new int[tabelaModelo.getTotalColunas()];
		podeEditar = new boolean[tabelaModelo.getTotalColunas()];
		for (int i = 0; i < tabelaModelo.getTotalColunas(); i++) {
			largura[i] = tabelaModelo.getLargura(i);
			podeEditar[i] = false;
		}
	}
	private VeiculoDocumento veiculoDocumento;

	private List<VeiculoDocumento> veiculoDocumentoList = new LinkedList<>();

	public VeiculoDocumentoTm() {

	}

	public VeiculoDocumentoTm(List<VeiculoDocumento> lista) {
		veiculoDocumentoList.addAll(lista);
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
		return veiculoDocumentoList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		VeiculoDocumento veiculoDocumento = veiculoDocumentoList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return veiculoDocumento.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("SITUAÇÃO")) {
			return veiculoDocumento.getSituacaoVeiculoDocumento();
		}
		if (tabelaModelo.getNome(columnIndex).equals("LOCAL")) {
			return veiculoDocumento.getLocalVeiculoDocumento();
		}
		if (tabelaModelo.getNome(columnIndex).equals("PLACA")) {
			return veiculoDocumento.getVeiculo().getPlaca();
		}
		if (tabelaModelo.getNome(columnIndex).equals("MARCA")) {
			return veiculoDocumento.getVeiculo().getMarca().getMarca();
		}
		if (tabelaModelo.getNome(columnIndex).equals("MODELO")) {
			return veiculoDocumento.getVeiculo().getModelo().getModelo();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME DO PROPRIETÁRIO")) {
			return veiculoDocumento.getVeiculo().getProprietarioNome();
		}
		if (tabelaModelo.getNome(columnIndex).equals("DATA DE RECEBIMENTO DO DOCUMENTO")) {
			return veiculoDocumento.getDataRecebimentoVeiculoDocumento();
		}
		if (tabelaModelo.getNome(columnIndex).equals("DATA DE DEVOLUÇÃO DO DOCUMENTO")) {
			return veiculoDocumento.getDataDevolucaoVeiculoDocumento();
		}
		return veiculoDocumento;
	}

	public VeiculoDocumento getVeiculoDocumento(int linha) {
		if (veiculoDocumentoList.size() > 0) {
			return veiculoDocumentoList.get(linha);
		}
		return null;
	}

	public List<VeiculoDocumento> getVeiculoDocumentoList() {
		return veiculoDocumentoList;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		veiculoDocumento = veiculoDocumentoList.get(rowIndex);

		Veiculo veiculo = new Veiculo();
		List<Veiculo> listVeiculo = null;
		if (tabelaModelo.getNome(columnIndex).equals("PLACA")) {
			veiculo.setPlaca(aValue.toString());
			listVeiculo = (List<Veiculo>) VeiculoFac.pesquisarRegistro(veiculo);
			veiculo = listVeiculo.get(0);
			veiculoDocumento.setVeiculo(veiculo);
		}

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			veiculoDocumento.setId(Long.parseLong(aValue.toString()));
		}

		fireTableDataChanged();
	}

	public void setVeiculoDocumentoList(List<VeiculoDocumento> veiculoDocumento) {
		veiculoDocumentoList = veiculoDocumento;
	}
}