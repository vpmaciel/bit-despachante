package erp.financeiro.contasreceber;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class ContasReceberSel implements ListSelectionListener {

	JTable table;

	ContasReceberSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				ContasReceber contasReceberPesquisaRegistro = new ContasReceber();
				contasReceberPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], ContasReceberTm.ID));

				if (table.getSelectedRow() != -1) {
					ContasReceber contasReceber = ContasReceberFac.getRegistro(contasReceberPesquisaRegistro);
					ContasReceberTm contasReceberTm = (ContasReceberTm) table.getModel();
					contasReceberTm.getContasReceber(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getContasReceberJanCad());
					MainControl.getContasReceberJanCad().getContasReceberCont().setContasReceber(contasReceber);
					MainControl.getContasReceberJanCad().getContasReceberCont().atualizarGui();
					MainControl.getContasReceberJanCad().setFocusable(true);
					MainControl.getContasReceberJanPesq().setVisible(false);
				}
			}
		}
	}
}
