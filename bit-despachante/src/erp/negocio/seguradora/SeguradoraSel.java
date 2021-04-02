package erp.negocio.seguradora;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class SeguradoraSel implements ListSelectionListener {

	JTable table;

	SeguradoraSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Seguradora seguradoraPesquisaRegistro = new Seguradora();
				seguradoraPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], SeguradoraTm.ID));

				if (table.getSelectedRow() != -1) {
					Seguradora seguradora = SeguradoraFac.getRegistro(seguradoraPesquisaRegistro);
					SeguradoraTm seguradoraTm = (SeguradoraTm) table.getModel();
					seguradoraTm.getSeguradora(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getSeguradoraJanCad());
					MainControl.getSeguradoraJanCad().getSeguradoraCont().setSeguradora(seguradora);
					MainControl.getSeguradoraJanCad().getSeguradoraCont().atualizarGui();
					MainControl.getSeguradoraJanCad().setFocusable(true);
					MainControl.getSeguradoraJanPesq().setVisible(false);
				}
			}
		}
	}
}
