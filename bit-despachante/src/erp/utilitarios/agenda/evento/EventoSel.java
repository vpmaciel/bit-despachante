package erp.utilitarios.agenda.evento;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class EventoSel implements ListSelectionListener {

	JTable table;

	EventoSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Evento eventoPesquisaRegistro = new Evento();
				eventoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], EventoTm.ID));

				if (table.getSelectedRow() != -1) {
					Evento evento = EventoFac.getRegistro(eventoPesquisaRegistro);
					EventoTm eventoTm = (EventoTm) table.getModel();
					eventoTm.getEvento(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getAgendaEventoJanCad());
					MainControl.getAgendaEventoJanCad().getEventoCont().setEvento(evento);
					MainControl.getAgendaEventoJanCad().getEventoCont().atualizarGui();
					MainControl.getAgendaEventoJanCad().setFocusable(true);
					MainControl.getAgendaEventoJanPesq().setVisible(false);
				}
			}
		}
	}
}
