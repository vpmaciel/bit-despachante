package erp.escritorio.veiculo.seguro;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class SeguroSel implements ListSelectionListener {

	JTable table;

	SeguroSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Seguro seguroPesquisaRegistro = new Seguro();
				seguroPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], SeguroTm.ID));

				if (table.getSelectedRow() != -1) {
					Seguro seguro = SeguroFac.getRegistro(seguroPesquisaRegistro);
					SeguroTm seguroTm = (SeguroTm) table.getModel();
					seguroTm.getSeguro(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getSeguroJanCad());
					MainControl.getSeguroJanCad().getSeguroCont().setSeguro(seguro);
					MainControl.getSeguroJanCad().getSeguroCont().atualizarGui();
					MainControl.getSeguroJanCad().setFocusable(true);
					MainControl.getSeguroJanPesq().setVisible(false);
				}
			}
		}
	}
}
