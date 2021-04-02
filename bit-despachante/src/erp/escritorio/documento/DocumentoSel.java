package erp.escritorio.documento;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class DocumentoSel implements ListSelectionListener {

	JTable table;

	DocumentoSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Documento veiculoDocumentoPesquisaRegistro = new Documento();
				veiculoDocumentoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], DocumentoTm.ID));

				if (table.getSelectedRow() != -1) {
					Documento veiculoDocumento = DocumentoFac.getRegistro(veiculoDocumentoPesquisaRegistro);
					DocumentoTm veiculoDocumentoTm = (DocumentoTm) table.getModel();
					veiculoDocumentoTm.getDocumento(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getDocumentoJanCad());
					MainControl.getDocumentoJanCad().getDocumentoCont().setDocumento(veiculoDocumento);
					MainControl.getDocumentoJanCad().getDocumentoCont().atualizarGui();
					MainControl.getDocumentoJanCad().setFocusable(true);
					MainControl.getDocumentoJanPesq().setVisible(false);
				}
			}
		}
	}
}
