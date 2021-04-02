package erp.financeiro.cheque;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class ChequeSel implements ListSelectionListener {

	JTable table;

	ChequeSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Cheque chequePesquisaRegistro = new Cheque();
				chequePesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], ChequeTm.ID));

				if (table.getSelectedRow() != -1) {
					Cheque cheque = ChequeFac.getRegistro(chequePesquisaRegistro);
					ChequeTm chequeTm = (ChequeTm) table.getModel();
					chequeTm.getCheque(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getChequeJanCad());
					MainControl.getChequeJanCad().getChequeCont().setCheque(cheque);
					MainControl.getChequeJanCad().getChequeCont().atualizarGui();
					MainControl.getChequeJanCad().setFocusable(true);
					MainControl.getChequeJanPesq().setVisible(false);
				}
			}
		}
	}
}
