package erp.escritorio.documento;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import erp.arquitetura.util.TabelaModelo;

@SuppressWarnings("serial")
public class DocumentoTm extends AbstractTableModel {

	public static final int ID = 0;
	public static int[] largura;
	private static boolean[] podeEditar;
	private static TabelaModelo tabelaModelo = new TabelaModelo();
	static {
		tabelaModelo.adicionarColuna("ID", 0, 100);
		tabelaModelo.adicionarColuna("SITUAÇÃO", 1, 200);
		tabelaModelo.adicionarColuna("LOCAL", 2, 200);
		tabelaModelo.adicionarColuna("TIPO DE DOCUMENTO", 3, 200);
		tabelaModelo.adicionarColuna("NOME DO PROPRIETÁRIO", 4, 300);
		tabelaModelo.adicionarColuna("MÊS DE RECEBIMENTO DO DOCUMENTO", 5, 300);
		tabelaModelo.adicionarColuna("ANO DE RECEBIMENTO DO DOCUMENTO", 6, 300);

		largura = new int[tabelaModelo.getTotalColunas()];
		podeEditar = new boolean[tabelaModelo.getTotalColunas()];
		for (int i = 0; i < tabelaModelo.getTotalColunas(); i++) {
			largura[i] = tabelaModelo.getLargura(i);
			podeEditar[i] = false;
		}
	}
	private Documento documento;

	private List<Documento> documentoList = new LinkedList<>();

	public DocumentoTm() {

	}

	public DocumentoTm(List<Documento> lista) {
		documentoList.addAll(lista);
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

	public Documento getDocumento(int linha) {
		if (documentoList.size() > 0) {
			return documentoList.get(linha);
		}
		return null;
	}

	public List<Documento> getDocumentoList() {
		return documentoList;
	}

	@Override
	public int getRowCount() {
		return documentoList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Documento documento = documentoList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			return documento.getId();
		}
		if (tabelaModelo.getNome(columnIndex).equals("SITUAÇÃO")) {
			return documento.getSituacaoDocumento();
		}
		if (tabelaModelo.getNome(columnIndex).equals("LOCAL")) {
			return documento.getLocalDocumento();
		}
		if (tabelaModelo.getNome(columnIndex).equals("TIPO DE DOCUMENTO")) {
			return documento.getTipoDocumento();
		}
		if (tabelaModelo.getNome(columnIndex).equals("NOME DO PROPRIETÁRIO")) {
			return documento.getNomeProprietario();
		}
		if (tabelaModelo.getNome(columnIndex).equals("MÊS DE RECEBIMENTO DO DOCUMENTO")) {
			return documento.getMesRecebimentoDocumento();
		}
		if (tabelaModelo.getNome(columnIndex).equals("ANO DE RECEBIMENTO DO DOCUMENTO")) {
			return documento.getAnoRecebimentoDocumento();
		}
		return documento;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return podeEditar[columnIndex];
	}

	public void setDocumentoList(List<Documento> documento) {
		documentoList = documento;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		documento = documentoList.get(rowIndex);

		if (tabelaModelo.getNome(columnIndex).equals("ID")) {
			documento.setId(Long.parseLong(aValue.toString()));
		}

		fireTableDataChanged();
	}
}