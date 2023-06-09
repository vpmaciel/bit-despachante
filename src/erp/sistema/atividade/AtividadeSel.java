package erp.sistema.atividade;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class AtividadeSel implements ListSelectionListener {

	JTable table;

	AtividadeSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Atividade usuarioPesquisaRegistro = new Atividade();
				usuarioPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], AtividadeTm.ID));

				if (table.getSelectedRow() != -1) {
					Atividade atividade = ((List<Atividade>) AtividadeFac.pesquisarRegistro(usuarioPesquisaRegistro)).get(0);
					AtividadeTm atividadeTm = (AtividadeTm) table.getModel();
					atividadeTm.getAtividade(table.getSelectedRow());

					MainControl.mostrarFrame(MainControl.getAtividadeJan());
					MainControl.getAtividadeJan().getAtividadeCont().setAtividade(atividade);
					MainControl.getAtividadeJan().getAtividadeCont().atualizarGui();
					MainControl.getAtividadeJan().setFocusable(true);
					MainControl.getAtividadeJan().getTabbedPane().setSelectedIndex(0);
				}
			}
		}
	}
}
