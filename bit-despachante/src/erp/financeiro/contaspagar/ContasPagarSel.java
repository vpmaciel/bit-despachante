package erp.financeiro.contaspagar;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class ContasPagarSel implements ListSelectionListener {

	JTable table;

	ContasPagarSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				ContasPagar contasPagarPesquisaRegistro = new ContasPagar();
				contasPagarPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], ContasPagarTm.ID));

				if (table.getSelectedRow() != -1) {
					ContasPagar contasPagar = ContasPagarFac.getRegistro(contasPagarPesquisaRegistro);
					ContasPagarTm contasPagarTm = (ContasPagarTm) table.getModel();
					contasPagarTm.getContasPagar(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getContasPagarJanCad());
					MainControl.getContasPagarJanCad().getContasPagarCont().setContasPagar(contasPagar);
					MainControl.getContasPagarJanCad().getContasPagarCont().atualizarGui();
					MainControl.getContasPagarJanCad().setFocusable(true);
					MainControl.getContasPagarJanPesq().setVisible(false);
				}
			}
		}
	}
}
