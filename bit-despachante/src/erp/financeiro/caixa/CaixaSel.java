package erp.financeiro.caixa;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class CaixaSel implements ListSelectionListener {

	JTable table;

	CaixaSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Caixa caixaPesquisaRegistro = new Caixa();
				caixaPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], CaixaTm.ID));

				if (table.getSelectedRow() != -1) {
					Caixa caixa = CaixaFac.getRegistro(caixaPesquisaRegistro);
					CaixaTm caixaTm = (CaixaTm) table.getModel();
					caixaTm.getCaixa(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getCaixaJanCad());
					MainControl.getCaixaJanCad().getCaixaCont().setCaixa(caixa);
					MainControl.getCaixaJanCad().getCaixaCont().atualizarGui();
					MainControl.getCaixaJanCad().setFocusable(true);
					MainControl.getCaixaJanPesq().setVisible(false);
				}
			}
		}
	}
}
