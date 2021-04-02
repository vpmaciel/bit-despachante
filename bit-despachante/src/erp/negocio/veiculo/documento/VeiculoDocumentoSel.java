package erp.negocio.veiculo.documento;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class VeiculoDocumentoSel implements ListSelectionListener {

	JTable table;

	VeiculoDocumentoSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				VeiculoDocumento veiculoDocumentoPesquisaRegistro = new VeiculoDocumento();
				veiculoDocumentoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], VeiculoDocumentoTm.ID));

				if (table.getSelectedRow() != -1) {
					VeiculoDocumento veiculoDocumento = VeiculoDocumentoFac
							.getRegistro(veiculoDocumentoPesquisaRegistro);
					VeiculoDocumentoTm veiculoDocumentoTm = (VeiculoDocumentoTm) table.getModel();
					veiculoDocumentoTm.getVeiculoDocumento(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getVeiculoDocumentoJanCad());
					MainControl.getVeiculoDocumentoJanCad().getVeiculoDocumentoCont()
							.setVeiculoDocumento(veiculoDocumento);
					MainControl.getVeiculoDocumentoJanCad().getVeiculoDocumentoCont().atualizarGui();
					MainControl.getVeiculoDocumentoJanCad().setFocusable(true);
					MainControl.getVeiculoDocumentoJanPesq().setVisible(false);
				}
			}
		}
	}
}
