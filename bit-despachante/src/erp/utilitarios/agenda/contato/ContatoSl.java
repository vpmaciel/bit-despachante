package erp.utilitarios.agenda.contato;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class ContatoSl implements ListSelectionListener {

	JTable table;

	ContatoSl(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Contato recadoPesquisaRegistro = new Contato();
				recadoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], ContatoTm.ID));

				if (table.getSelectedRow() != -1) {
					Contato contato = ContatoFac.getRegistro(recadoPesquisaRegistro);
					ContatoTm contatoTm = (ContatoTm) table.getModel();
					contatoTm.getContato(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getAgendaContatoJanCad());
					MainControl.getAgendaContatoJanCad().getContatoCont().setContato(contato);
					MainControl.getAgendaContatoJanCad().getContatoCont().atualizarGui();
					MainControl.getAgendaContatoJanCad().setFocusable(true);
					MainControl.getAgendaContatoJanPesq().setVisible(false);
				}
			}
		}
	}
}
