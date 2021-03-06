package erp.utilitarios.agenda.recado;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class RecadoSel implements ListSelectionListener {

	JTable table;

	RecadoSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Recado recadoPesquisaRegistro = new Recado();
				recadoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], RecadoTm.ID));

				if (table.getSelectedRow() != -1) {
					Recado recado = RecadoFac.getRegistro(recadoPesquisaRegistro);
					RecadoTm recadoTm = (RecadoTm) table.getModel();
					recadoTm.getRecado(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getAgendaRecadoJanCad());
					MainControl.getAgendaRecadoJanCad().getRecadoCont().setRecado(recado);
					MainControl.getAgendaRecadoJanCad().getRecadoCont().atualizarGui();
					MainControl.getAgendaRecadoJanCad().setFocusable(true);
					MainControl.getAgendaRecadoJanPesq().setVisible(false);
				}
			}
		}
	}
}